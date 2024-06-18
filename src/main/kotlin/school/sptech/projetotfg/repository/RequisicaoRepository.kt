package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicoesCumResponseDTO
import school.sptech.projetotfg.dto.RequisicoesReqResponseDTO

@Repository
interface RequisicaoRepository:JpaRepository<Requisicoes, Long> {

    //fun countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(assunto:String,// situacao:Long, inicioTri:Long, fimTri:Long):Int

    @Query("""
        SELECT NEW school.sptech.projetotfg.dto.RequisicoesReqResponseDTO(
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5)
        ) FROM Requisicoes r WHERE r.id = 1 
    """)
    fun findTop1():RequisicoesReqResponseDTO?






    @Query("""
        SELECT NEW school.sptech.projetotfg.dto.RequisicoesCumResponseDTO(
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6)
        )
        FROM Requisicoes r WHERE r.calendario.id = 1
    """)
    fun findFirst():RequisicoesCumResponseDTO?

}