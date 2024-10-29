package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.RendaFamiliar
import java.util.Optional

interface RendaFamiliaRepository : JpaRepository<RendaFamiliar,Long> {

    fun findByRenda(renda:Double):Optional<RendaFamiliar>

}