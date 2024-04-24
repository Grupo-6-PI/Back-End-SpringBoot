package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.AcessoUser

interface AcessoRepository : JpaRepository<AcessoUser, Int> {
    fun findByEmail(email: String): AcessoUser?
}
