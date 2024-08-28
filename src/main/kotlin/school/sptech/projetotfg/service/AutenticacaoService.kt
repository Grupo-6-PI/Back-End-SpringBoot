package school.sptech.projetotfg.service

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.gerenciamento.Acesso
import school.sptech.projetotfg.dto.LoginRequestDTO

import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.AcessoRepository
import school.sptech.projetotfg.repository.SituacaoRepository
import school.sptech.projetotfg.repository.UsuarioRepository
import java.time.LocalDate

@Service
class AutenticacaoService(
    private val usuarioRepository: UsuarioRepository,
    private val acessoRepository: AcessoRepository,
    private val situacaoRepository: SituacaoRepository
):school.sptech.projetotfg.complement.Service() {

    fun login(request: LoginRequestDTO): UsuarioResponseDTO {

        val autenticacao = usuarioRepository.existsByEmail(request.email)

        if (autenticacao != true){
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Usuário inexistente, por favor realize o cadastro antes do login")
        }

        val usuario = usuarioRepository.buscarEmail(request.email)

        if (usuario!!.getSenha() != request.senha) {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Senha incorreta")
        }

        val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)

        if (ultimoAcesso != null && ultimoAcesso.getSituacao()!!.getSituacao() == "Logado") {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Usuário já está logado em outro dispositivo")
        }

        val situacaoLogado = situacaoRepository.findBySituacao("Logado")
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(500), "Situação 'Logado' não encontrada")

        val acesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoLogado,
            usuario = usuario
        )

        acessoRepository.save(acesso)

        return UsuarioResponseDTO(usuario.getId()!!, usuario.getNome()!!, usuario.getEmail()!!)
    }

    fun logoff(usuarioId: Long) {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado") }

        val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(400), "Nenhum acesso encontrado para o usuário")

        if (ultimoAcesso.getSituacao()!!.getSituacao() != "Logado") {
            throw ResponseStatusException(HttpStatusCode.valueOf(400), "Usuário não está logado")
        }

        val situacaoDeslogado = situacaoRepository.findBySituacao("Deslogado")
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(500), "Situação 'Deslogado' não encontrada")

        val novoAcesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoDeslogado,
            usuario = usuario
        )

        acessoRepository.save(novoAcesso)
    }

}
