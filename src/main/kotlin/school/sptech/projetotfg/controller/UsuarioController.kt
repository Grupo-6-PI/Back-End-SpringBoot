package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.UsuarioCompletoInputDTO
import school.sptech.projetotfg.dto.UsuarioCompletoResponseDTO
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.service.UsuarioService

@RestController
@RequestMapping("/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("/cadastro")
    fun cadastrarUsuario(@RequestBody usuarioInputDTO: UsuarioInputDTO): ResponseEntity<UsuarioResponseDTO> {
        try {

            val usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioInputDTO)
            return ResponseEntity.status(201).body(usuarioResponseDTO)

        } catch (ex: ResponseStatusException) {

            return ResponseEntity.status(500).build()

        }
    }

    @PutMapping("/{id}")
    fun atualizarUsuario(
        @PathVariable id: Long,
        @RequestBody usuarioInputDTO: UsuarioInputDTO
    ): ResponseEntity<UsuarioResponseDTO> {
        try {
            val usuarioResponseDTO = usuarioService.atualizarUsuario(id, usuarioInputDTO)
            return ResponseEntity.status(200).body(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }

    @GetMapping
    fun listarUsuarios(): ResponseEntity<List<UsuarioResponseDTO>> {
        val usuarios = usuarioService.listarUsuarios()
        return ResponseEntity.ok(usuarios)
    }

    @DeleteMapping("/{id}")
    fun excluirUsuario(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            var usuarioResponseDTO = usuarioService.encontrarUsuario(id)
            usuarioService.excluirUsuario(id)
            return ResponseEntity.status(200).body(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).body("Usuário não encontrado")
        }
    }

    @PostMapping("/completo/cadastro")
    fun cadastrarUsuarioCompleto(@RequestBody usuarioCompletoInputDTO: UsuarioCompletoInputDTO): ResponseEntity<UsuarioCompletoResponseDTO> {
        try {

            val usuarioCompletoResponseDTO = usuarioService.cadastrarUsuarioCompleto(usuarioCompletoInputDTO)
            return ResponseEntity.status(201).body(usuarioCompletoResponseDTO)

        } catch (ex: ResponseStatusException) {

            return ResponseEntity.status(500).build()

        }
    }

    @PutMapping("/completo/{id}")
    fun atualizarUsuarioCompleto(
        @PathVariable id: Long,
        @RequestBody usuarioCompletoInputDTO: UsuarioCompletoInputDTO
    ): ResponseEntity<UsuarioCompletoResponseDTO> {
        try {
            val usuarioCompletoResponseDTO = usuarioService.atualizarUsuarioCompleto(id, usuarioCompletoInputDTO)
            return ResponseEntity.status(200).body(usuarioCompletoResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }

    @GetMapping("/completo")
    fun listarUsuariosCompletos(): ResponseEntity<List<UsuarioCompletoResponseDTO>> {
        val usuarios = usuarioService.listarUsuariosCompletos()
        return ResponseEntity.ok(usuarios)
    }

}
