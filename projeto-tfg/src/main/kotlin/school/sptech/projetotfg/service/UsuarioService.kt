package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Acesso
import school.sptech.projetotfg.dto.LoginRequestDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.AcessoRepository
import school.sptech.projetotfg.repository.SituacaoRepository
import school.sptech.projetotfg.repository.UsuarioRepository
import java.time.LocalDate

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val acessoRepository: AcessoRepository,
    private val situacaoRepository: SituacaoRepository,
    private val mapper: ModelMapper = ModelMapper()
) {

    fun login(dto: LoginRequestDTO): UsuarioResponseDTO {
        val usuario = usuarioRepository.findByEmail(dto.email)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        if (usuario.senha != dto.senha) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida")
        }

        val situacaoLogado = situacaoRepository.findBySituacao("Logado")
            .orElseThrow { ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Situação 'Logado' não encontrada") }

        val acesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoLogado,
            usuario = usuario
        )

        try {
            acessoRepository.save(acesso)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao registrar acesso")
        }

        return mapper.map(usuario, UsuarioResponseDTO::class.java)
    }

    fun logoff(usuarioId: Long) {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        val ultimoAcesso = acessoRepository.findFirstByUsuarioOrderByDataAcessoDesc(usuario)
            .orElseThrow { ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum registro de acesso encontrado") }

        if (ultimoAcesso.getSituacao().situacao != "Logado") {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não está logado")
        }

        val situacaoDeslogado = situacaoRepository.findBySituacao("Deslogado")
            .orElseThrow { ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Situação 'Deslogado' não encontrada") }

        val novoAcesso = Acesso(
            dataAcesso = LocalDate.now(),
            situacao = situacaoDeslogado,
            usuario = usuario
        )

        acessoRepository.save(novoAcesso)
    }


}
