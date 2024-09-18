package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.views.login
import java.util.Optional

interface LoginRepository : JpaRepository<login,Long> {

    fun findByEmail(email:String):Optional<login>

}