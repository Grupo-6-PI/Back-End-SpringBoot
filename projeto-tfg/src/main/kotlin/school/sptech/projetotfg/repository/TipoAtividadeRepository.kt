package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.TipoAtividade

interface TipoAtividadeRepository : JpaRepository<TipoAtividade, Long>
