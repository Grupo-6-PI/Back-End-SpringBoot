package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.Calendario

interface CalendarioRepository : JpaRepository<Calendario, Long>
