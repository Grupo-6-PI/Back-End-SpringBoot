package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.service.UsuarioService

@RestController
@RequestMapping("/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("/cadastro")
    fun cadastrarUsuario(@RequestBody usuarioInputDTO: UsuarioInputDTO): ResponseEntity<Any> {
        return try {
            val usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioInputDTO)
            ResponseEntity.ok(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            ResponseEntity.status(500).body("Erro interno ao cadastrar usuário")
        }
    }

    @PutMapping("/{id}")
    fun atualizarUsuario(
        @PathVariable id: Long,
        @RequestBody usuarioInputDTO: UsuarioInputDTO
    ): ResponseEntity<Any> {
        return try {
            val usuarioResponseDTO = usuarioService.atualizarUsuario(id, usuarioInputDTO)
            ResponseEntity.ok(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            ResponseEntity.status(404).body("Usuário não encontrado")
        }
    }

    @GetMapping
    fun listarUsuarios(): ResponseEntity<List<UsuarioResponseDTO>> {
        val usuarios = usuarioService.listarUsuarios()
        return ResponseEntity.ok(usuarios)
    }

    @DeleteMapping("/{id}")
    fun excluirUsuario(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            usuarioService.excluirUsuario(id)
            ResponseEntity.noContent().build()
        } catch (ex: ResponseStatusException) {
            ResponseEntity.status(404).body("Usuário não encontrado")
        }
    }
}
