package school.sptech.projetotfg.repository

import school.sptech.projetotfg.domain.cadastro.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository:JpaRepository<Usuario,Long> {
        fun findByEmail(email: String): Optional<Usuario>

}