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
                id = usuarioSalvo.getId(),
                nome = usuarioSalvo.getNome(),
                email = usuarioSalvo.getEmail()
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun atualizarUsuario(id: Long, usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.setNome(usuarioInputDTO.nome)
        usuarioExistente.setEmail(usuarioInputDTO.email)
        usuarioExistente.setSenha(usuarioInputDTO.senha)

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioResponseDTO(
                id = usuarioAtualizado.getId(),
                nome = usuarioAtualizado.getNome(),
                email = usuarioAtualizado.getEmail()
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuarios(): List<UsuarioResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioResponseDTO(
                id = usuario.getId(),
                nome = usuario.getNome(),
                email = usuario.getEmail()
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
                id = usuarioSalvo.getId(),
                nome = usuarioSalvo.getNome(),
                email = usuarioSalvo.getEmail(),
                senha = null,
                informacoesAdicionais = usuarioSalvo.getInformacoesAdicionais(),
                situacao = usuarioSalvo.getSituacao(),
                nivelAcesso = usuarioSalvo.getNivelAcesso()
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun atualizarUsuarioCompleto(id: Long, usuarioCompletoInputDTO: UsuarioCompletoInputDTO): UsuarioCompletoResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.setNome(usuarioCompletoInputDTO.nome)
        usuarioExistente.setEmail(usuarioCompletoInputDTO.email)
        usuarioExistente.setSenha(usuarioCompletoInputDTO.senha)
        usuarioExistente.setInformacoesAdicionais(usuarioCompletoInputDTO.informacoesAdicionais!!)
        usuarioExistente.setSituacao(usuarioCompletoInputDTO.situacao!!)
        usuarioExistente.setNivelAcesso(usuarioCompletoInputDTO.nivelAcesso!!)

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioCompletoResponseDTO(
                id = usuarioAtualizado.getId(),
                nome = usuarioAtualizado.getNome(),
                email = usuarioAtualizado.getEmail(),
                senha = usuarioAtualizado.getSenha(),
                informacoesAdicionais = usuarioAtualizado.getInformacoesAdicionais(),
                situacao = usuarioAtualizado.getSituacao(),
                nivelAcesso = usuarioAtualizado.getNivelAcesso()
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuariosCompletos(): List<UsuarioCompletoResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioCompletoResponseDTO(
                id = usuario.getId(),
                nome = usuario.getNome(),
                email = usuario.getEmail(),
                senha = null,
                informacoesAdicionais = usuario.getInformacoesAdicionais(),
                situacao = usuario.getSituacao(),
                nivelAcesso = usuario.getNivelAcesso()
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
