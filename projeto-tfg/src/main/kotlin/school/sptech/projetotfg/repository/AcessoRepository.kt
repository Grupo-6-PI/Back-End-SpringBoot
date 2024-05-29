package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Acesso
import school.sptech.projetotfg.domain.cadastro.Usuario
import java.util.*

interface AcessoRepository : JpaRepository<Acesso, Long> {
        fun findFirstByUsuarioOrderByDataAcessoDesc(usuario: Usuario): Optional<Acesso>


}
