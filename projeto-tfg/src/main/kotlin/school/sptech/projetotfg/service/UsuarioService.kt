package school.sptech.projetotfg.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.UsuarioRepository
import school.sptech.projetotfg.domain.cadastro.Usuario

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) {
    fun cadastrarUsuario(usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        if (usuarioRepository.existsByEmail(usuarioInputDTO.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado")
        }

        val usuario = Usuario(
            nome = usuarioInputDTO.nome,
            email = usuarioInputDTO.email,
            senha = usuarioInputDTO.senha,
            informacoesAdicionaisId = null,
            situacao = null,
            nivelAcessoId = null
        )

        return try {
            val usuarioSalvo = usuarioRepository.save(usuario)
            UsuarioResponseDTO(
                id = usuarioSalvo.id,
                nome = usuarioSalvo.nome,
                email = usuarioSalvo.email
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun atualizarUsuario(id: Long, usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.nome = usuarioInputDTO.nome
        usuarioExistente.email = usuarioInputDTO.email
        usuarioExistente.senha = usuarioInputDTO.senha

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioResponseDTO(
                id = usuarioAtualizado.id,
                nome = usuarioAtualizado.nome,
                email = usuarioAtualizado.email
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuarios(): List<UsuarioResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioResponseDTO(
                id = usuario.id,
                nome = usuario.nome,
                email = usuario.email
            )
        }
    }

    fun excluirUsuario(id: Long) {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {
            usuarioRepository.deleteById(id)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir usuário: ${ex.message}")
        }
    }
}
