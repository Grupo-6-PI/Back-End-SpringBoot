package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.util.Optional

interface SituacaoRepository : JpaRepository<Situacao, Int> {
    fun findBySituacao(situacao: String): Optional<Situacao>
}