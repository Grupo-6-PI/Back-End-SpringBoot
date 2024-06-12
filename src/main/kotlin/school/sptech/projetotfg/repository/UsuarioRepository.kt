package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetotfg.domain.cadastro.Usuario

interface UsuarioRepository : JpaRepository<Usuario, Long> {
        fun existsByEmail(email: String): Boolean

        @Query("SELECT NEW Usuario(l.id,l.nome,l.email,l.senha) FROM login l WHERE l.email = :email")
        fun buscarEmail(email: String): Usuario?
}
