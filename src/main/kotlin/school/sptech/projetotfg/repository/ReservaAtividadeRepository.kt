package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import java.util.Optional

interface ReservaAtividadeRepository : JpaRepository<ReservaAtividade, Long> {
    fun findAllByCalendarioId(calendario: Long): List<ReservaAtividade>

    fun existsByAtividadeId(id:Long): Boolean

    fun findByAtividadeId(id:Long):Optional<ReservaAtividade>

}
