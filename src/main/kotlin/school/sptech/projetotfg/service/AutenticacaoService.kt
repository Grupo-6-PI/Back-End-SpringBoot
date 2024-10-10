package school.sptech.projetotfg.service

import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.gerenciamento.Acesso
import school.sptech.projetotfg.dto.LoginRequestDTO

import school.sptech.projetotfg.dto.UsuarioResponseLoginDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDate

@Service
class AutenticacaoService(
    private val usuarioRepository: UsuarioRepository,
    private val acessoRepository: AcessoRepository,
    private val situacaoRepository: SituacaoRepository,
    private val loginRepository:LoginRepository,
    private val nivelAcessoRepository:NivelAcessoRepository
):school.sptech.projetotfg.complement.Service() {

    fun login(request: LoginRequestDTO): UsuarioResponseLoginDTO {

        val autenticacao = usuarioRepository.existsByEmail(request.email)

        if (autenticacao != true){
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Usuário inexistente, por favor realize o cadastro antes do login")
        }

        val usuario = usuarioRepository.buscarEmail(request.email)

        if (usuario!!.getSenha() != request.senha) {
            throw ResponseStatusException(HttpStatusCode.valueOf(401), "Senha incorreta")
        }

        //val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)

        //if (ultimoAcesso != null && ultimoAcesso.getSituacao()?.getSituacao() == "Logado") {
        //    throw ResponseStatusException(HttpStatusCode.valueOf(401), "Usuário já está logado em outro dispositivo")
        //}

        val situacaoLogado = situacaoRepository.findBySituacao("Logado")
         ?: throw ResponseStatusException(HttpStatusCode.valueOf(500), "Situação 'Logado' não encontrada")

        val acesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoLogado,
            usuario = usuario
        )

        acessoRepository.save(acesso)

        var view = loginRepository.findByEmail(usuario.getEmail()!!).get()

        var nivelAcesso = nivelAcessoRepository.findById(view.getNivelAcesso()!!).get()

        var resposta = UsuarioResponseLoginDTO(view.getId()!!,view.getNome()!!,view.getEmail()!!,nivelAcesso)

        return resposta
    }

    fun logoff(usuarioId: Long) {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado") }

        val ultimoAcesso = acessoRepository.findTopByUsuarioOrderByIdDesc(usuario)
            ?: throw ResponseStatusException(HttpStatusCode.valueOf(400), "Nenhum acesso encontrado para o usuário")

        //if (ultimoAcesso.getSituacao()!!.getSituacao() != "Logado") {
        //    throw ResponseStatusException(HttpStatusCode.valueOf(400), "Usuário não está logado")
        //}

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
