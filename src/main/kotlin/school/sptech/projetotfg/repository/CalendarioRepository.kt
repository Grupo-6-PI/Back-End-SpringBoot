package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.Calendario
import java.util.*

interface CalendarioRepository : JpaRepository<Calendario, Long> {
    fun findByAnoAndMesNumeracaoAndDiaNumeracao(ano: Int, mesNumeracao: Int, diaNumeracao: Int): Optional<Calendario>
}
