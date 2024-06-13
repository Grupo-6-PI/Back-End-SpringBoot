package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import school.sptech.projetotfg.domain.doacao.Requisicoes

@Repository
interface RequisicaoRepository:JpaRepository<Requisicoes,Long> {
    fun countByAssuntoRequisicaoAssuntoAndCalendarioDiaNumeracaoBetween(assunto:String, inicioTri:Int, fimTri:Int):Int
}