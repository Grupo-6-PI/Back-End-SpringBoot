package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.dto.LoginRequestDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.service.UsuarioService


@RestController
@RequestMapping("/acesso")
class AcessoController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("/login")
    fun login(dto: LoginRequestDTO): ResponseEntity<UsuarioResponseDTO> {
        val logar = usuarioService.login(dto)
        return ResponseEntity.status(200).body(logar)
    }

    @PostMapping("/logoff")
    fun logoff(usuarioId: Int): ResponseEntity<Unit> {
        val deslogar = usuarioService.logoff(usuarioId.toLong())
        return ResponseEntity.status(200).body(deslogar)
    }

}
