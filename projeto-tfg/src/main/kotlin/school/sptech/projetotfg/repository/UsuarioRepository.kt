package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetotfg.domain.cadastro.Usuario

interface UsuarioRepository : JpaRepository<Usuario, Long> {
        fun existsByEmail(email: String): Boolean

        @Query("SELECT NEW Usuario(u.id,u.nome,u.email,CAST(AES_DECRYPT(u.senha,'chave') as STRING)) FROM Usuario u where u.email=:email")
        fun buscarEmail(email: String): Usuario?
}
