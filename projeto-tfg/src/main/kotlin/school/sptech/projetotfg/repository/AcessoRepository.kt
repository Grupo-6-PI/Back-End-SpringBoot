package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetotfg.domain.Acesso
import school.sptech.projetotfg.domain.Usuario
import java.util.*

interface AcessoRepository : JpaRepository<Acesso, Int> {
        fun findFirstByUsuarioOrderByDataAcessoDesc(usuario: Usuario): Optional<Acesso>


}
