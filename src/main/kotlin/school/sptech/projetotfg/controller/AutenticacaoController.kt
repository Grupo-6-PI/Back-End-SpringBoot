package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.dto.LoginRequestDTO
import school.sptech.projetotfg.dto.UsuarioResponseLoginDTO
import school.sptech.projetotfg.service.AutenticacaoService

@RestController
@RequestMapping("/autenticacao")
class AutenticacaoController(
    private val autenticacaoService: AutenticacaoService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): ResponseEntity<UsuarioResponseLoginDTO> {
        val usuarioResponseDto = autenticacaoService.login(request)
        return ResponseEntity.status(200).body(usuarioResponseDto)
    }

    @PostMapping("/logoff/{usuarioId}")
    fun logoff(@PathVariable usuarioId: Long): ResponseEntity<String> {
        autenticacaoService.logoff(usuarioId)
        return ResponseEntity.status(204).body("Usuário deslogado com sucesso")
    }

}
