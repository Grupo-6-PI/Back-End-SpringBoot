package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.atividades.ReservaAtividade

interface ReservaAtividadeRepository : JpaRepository<ReservaAtividade, Long> {
    fun findAllByCalendarioId(calendario: Long): List<ReservaAtividade>
}
