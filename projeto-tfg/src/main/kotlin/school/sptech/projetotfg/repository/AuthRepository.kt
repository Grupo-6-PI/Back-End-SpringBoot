package school.sptech.projetotfg.repository

import school.sptech.projetotfg.domain.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<AuthUser, Long> {
    fun findByEmail(email: String): AuthUser?
}
