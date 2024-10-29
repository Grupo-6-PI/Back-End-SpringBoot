package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Estado
import java.util.Optional

interface EstadoRepository : JpaRepository<Estado, Long> {

    fun findByNome(nome:String):Optional<Estado>

}