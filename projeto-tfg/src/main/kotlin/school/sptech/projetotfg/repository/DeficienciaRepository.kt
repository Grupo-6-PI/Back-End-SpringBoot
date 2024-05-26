package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Deficiencia

interface DeficienciaRepository : JpaRepository<Deficiencia, Int> {
    fun findByNomeDeficiencia(nomeDeficiencia: String): Deficiencia?
}