package BackEndTFG.Login_LogOff.Repository

import BackEndTFG.Login_LogOff.Dominio.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<AuthUser, Long> {
    fun findByEmail(email: String): AuthUser?
}
