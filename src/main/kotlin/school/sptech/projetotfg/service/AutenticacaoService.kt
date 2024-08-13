package school.sptech.projetotfg.service

import org.springframework.http.HttpStatus
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
) {

    fun login(request: LoginRequestDTO): UsuarioResponseDTO {
        val usuario = usuarioRepository.buscarEmail(request.email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")

        if (usuario.getSenha() != request.senha) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta")
        }

        val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)
        if (ultimoAcesso != null && ultimoAcesso.situacao.getSituacao() == "Logado") {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário já está logado")
        }


        val situacaoLogado = situacaoRepository.findBySituacao("Logado")
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Situação 'Logado' não encontrada")

        val acesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoLogado,
            usuario = usuario
        )

        acessoRepository.save(acesso)

        return UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail())
    }

    fun logoff(usuarioId: Long) {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum acesso encontrado para o usuário")

        if (ultimoAcesso.situacao.getSituacao() != "Logado") {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não está logado")
        }

        val situacaoDeslogado = situacaoRepository.findBySituacao("Deslogado")
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Situação 'Deslogado' não encontrada")

        val novoAcesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoDeslogado,
            usuario = usuario
        )

        acessoRepository.save(novoAcesso)
    }

}
