package BackEndTFG.Login_LogOff.Dominio

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class AuthUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_usuario: Int = 0,
    val email: String,
    val senha: String
)
