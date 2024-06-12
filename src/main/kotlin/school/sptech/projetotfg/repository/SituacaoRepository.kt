package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.gerenciamento.Situacao

interface SituacaoRepository : JpaRepository<Situacao, Long> {
    fun findBySituacao(situacao: String): Situacao?
}
