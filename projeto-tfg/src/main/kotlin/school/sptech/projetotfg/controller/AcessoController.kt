package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.repository.AcessoRepository
import school.sptech.projetotfg.repository.UsuarioRepository

@RestController
@RequestMapping("/acesso")
class AcessoController(private val AcessoRepository: AcessoRepository, private val UsuarioRepository: UsuarioRepository) {

    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam password: String): String {
        val user = UsuarioRepository.findByEmail(email)
        return if (user != null && user.senha == password) {
            "Login feito com sucesso"
        } else {
            "Nome ou senha invalidos"
        }
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
