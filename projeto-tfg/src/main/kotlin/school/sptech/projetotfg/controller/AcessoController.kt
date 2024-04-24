package school.sptech.projetotfg.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AcessoController(private val AcessoRepository: acessoRepository) {

    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam password: String): String {
        val user = acessoRepository.findByEmail(email)
        return if (user != null && user.senha == password) {
            "Login feito com sucesso"
        } else {
            "Nome ou senha invalidos"
        }
    }

    /*@PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        return try {
            authService.logout()
            ResponseEntity.ok("Logout successful")
        } catch (e: Exception) {
            ResponseEntity.status(404).body("Logout failed: ${e.message}")
        }
    }*/
}
