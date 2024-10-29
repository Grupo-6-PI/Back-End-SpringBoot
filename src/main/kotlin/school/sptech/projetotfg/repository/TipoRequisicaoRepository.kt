package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.doacao.AssuntoRequisicao

interface TipoRequisicaoRepository: JpaRepository<AssuntoRequisicao, Long> {
}