package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.UsuarioRepository
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.dto.UsuarioCompletoInputDTO
import school.sptech.projetotfg.dto.UsuarioCompletoResponseDTO

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val mapper: ModelMapper
) {
    fun cadastrarUsuario(usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        if (usuarioRepository.existsByEmail(usuarioInputDTO.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado")
        }

        val usuario = Usuario(
            id = null,
            nome = usuarioInputDTO.nome,
            email = usuarioInputDTO.email,
            senha = usuarioInputDTO.senha,
            informacoesAdicionais = null,
            situacao = null,
            nivelAcesso = null
        )

        return try {
            val usuarioSalvo = usuarioRepository.save(usuario)
            UsuarioResponseDTO(
                id = usuarioSalvo.id!!,
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
                id = usuarioAtualizado.id!!,
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
                id = usuario.id!!,
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

    fun encontrarUsuario(id: Long): UsuarioResponseDTO {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {

            var usuario = usuarioRepository.findById(id)
            var resposta = mapper.map(
                usuario,
                UsuarioResponseDTO::class.java
            )
            return resposta

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao encontrar o usuário: ${ex.message}")
        }
    }

    fun cadastrarUsuarioCompleto(usuarioCompletoInputDTO: UsuarioCompletoInputDTO): UsuarioCompletoResponseDTO {
        if (usuarioRepository.existsByEmail(usuarioCompletoInputDTO.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado")
        }

        val usuario = Usuario(
            id = null,
            nome = usuarioCompletoInputDTO.nome,
            email = usuarioCompletoInputDTO.email,
            senha = usuarioCompletoInputDTO.senha,
            informacoesAdicionais = usuarioCompletoInputDTO.informacoesAdicionais,
            situacao = usuarioCompletoInputDTO.situacao,
            nivelAcesso = usuarioCompletoInputDTO.nivelAcesso
        )

        return try {
            val usuarioSalvo = usuarioRepository.save(usuario)
            UsuarioCompletoResponseDTO(
                id = usuarioSalvo.id!!,
                nome = usuarioSalvo.nome,
                email = usuarioSalvo.email,
                senha = null,
                informacoesAdicionais = usuarioSalvo.informacoesAdicionais!!,
                situacao = usuarioSalvo.situacao!!,
                nivelAcesso = usuarioSalvo.nivelAcesso!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun atualizarUsuarioCompleto(id: Long, usuarioCompletoInputDTO: UsuarioCompletoInputDTO): UsuarioCompletoResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.nome = usuarioCompletoInputDTO.nome
        usuarioExistente.email = usuarioCompletoInputDTO.email
        usuarioExistente.senha = usuarioCompletoInputDTO.senha
        usuarioExistente.informacoesAdicionais = usuarioCompletoInputDTO.informacoesAdicionais
        usuarioExistente.situacao = usuarioCompletoInputDTO.situacao
        usuarioExistente.nivelAcesso = usuarioCompletoInputDTO.nivelAcesso

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioCompletoResponseDTO(
                id = usuarioAtualizado.id!!,
                nome = usuarioAtualizado.nome,
                email = usuarioAtualizado.email,
                senha = usuarioAtualizado.senha,
                informacoesAdicionais = usuarioAtualizado.informacoesAdicionais!!,
                situacao = usuarioAtualizado.situacao!!,
                nivelAcesso = usuarioAtualizado.nivelAcesso!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuariosCompletos(): List<UsuarioCompletoResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioCompletoResponseDTO(
                id = usuario.id!!,
                nome = usuario.nome,
                email = usuario.email,
                senha = null,
                informacoesAdicionais = usuario.informacoesAdicionais!!,
                situacao = usuario.situacao!!,
                nivelAcesso = usuario.nivelAcesso!!
            )
        }
    }

    fun encontrarUsuarioCompleto(id: Long): UsuarioCompletoResponseDTO {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {

            var usuario = usuarioRepository.findById(id)
            var resposta = mapper.map(
                usuario,
                UsuarioCompletoResponseDTO::class.java
            )
            return resposta

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao encontrar o usuário: ${ex.message}")
        }
    }

}
