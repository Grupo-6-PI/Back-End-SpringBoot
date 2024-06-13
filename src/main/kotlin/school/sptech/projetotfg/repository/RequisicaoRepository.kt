package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.service.Caso

interface RequisicaoRepository:JpaRepository<Requisicoes,Long> {
    fun countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto:String, inicioTri:Int, fimTri:Int, situacao:Int ):Int
}