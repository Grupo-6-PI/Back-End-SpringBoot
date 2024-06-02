package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Usuario

interface UsuarioRepository : JpaRepository<Usuario, Long> {
        fun findByEmail(email: String): Usuario?
}
