package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.doacao.Requisicoes

interface RequisicaoRepository:JpaRepository<Requisicoes,Int> {
}