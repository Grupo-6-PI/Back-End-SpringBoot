package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.projetotfg.domain.atividades.Calendario
import java.util.*

interface CalendarioRepository : JpaRepository<Calendario, Long> {
    fun findByAnoAndMesNumeracaoAndDiaNumeracao(ano: Int, mesNumeracao: Int, diaNumeracao: Int): Optional<Calendario>

    @Query("SELECT c.id FROM Calendario c WHERE c.id = (SELECT c1.id FROM Calendario c1 WHERE c1.diaNumeracao = :diaNumeracao AND c1.mesNumeracao = :mesNumeracao AND c1.ano = :ano)")
    fun getSemana(diaNumeracao: Int,mesNumeracao: Int, ano: Int):Optional<Long>

}
