package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.Usuario
import school.sptech.projetotfg.dto.cadastrarUsuarioResponse
import school.sptech.projetotfg.repository.AcessoRepository
import school.sptech.projetotfg.repository.UsuarioRepository
import org.modelmapper.ModelMapper
import school.sptech.projetotfg.domain.Acesso
import school.sptech.projetotfg.dto.LoginAcessoResponse

@RestController
@RequestMapping("/acesso")
class AcessoController(
    private val AcessoRepository: AcessoRepository,
    private val UsuarioRepository: UsuarioRepository,
    val mapper: ModelMapper= ModelMapper()
) {

    @PostMapping("/login")
    fun login(@RequestBody usuario: LoginAcessoResponse): ResponseEntity<String> {
        var usuarioLogado: Usuario = mapper.map(usuario,Usuario::class.java)

         return ResponseEntity.status(201).body("Login feito")
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        return try {
            val id = 1
            AcessoRepository.logout(id)
            ResponseEntity.ok("Logout successful")

        } catch (e: Exception) {
            ResponseEntity.status(404).body("Logout failed: ${e.message}")
        }
    }
}
