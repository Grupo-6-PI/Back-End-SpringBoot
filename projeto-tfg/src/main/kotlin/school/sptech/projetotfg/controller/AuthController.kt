package BackEndTFG.Login_LogOff.Controller

import BackEndTFG.Login_LogOff.Dominio.AuthUser
import BackEndTFG.Login_LogOff.Repository.AuthRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val authRepository: AuthRepository) {

    @PostMapping("/register")
    fun register(@RequestParam email: String, @RequestParam senha: String) {
        val newUser = AuthUser(email = email, senha = senha)
        authRepository.save(newUser)
    }

    @PostMapping("/login")
    fun login(@RequestParam email: String, @RequestParam password: String): String {
        val user = authRepository.findByEmail(email)
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
