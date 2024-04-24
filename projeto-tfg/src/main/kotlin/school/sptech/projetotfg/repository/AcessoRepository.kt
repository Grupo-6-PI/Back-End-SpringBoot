package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetotfg.domain.Acesso

interface AcessoRepository : JpaRepository<Acesso, Int> {
    @Query("SELECT situacao FROM Acesso WHERE id = 1")
    fun logout(id: Int): String {
            return("Logout executado!")
    }
}
