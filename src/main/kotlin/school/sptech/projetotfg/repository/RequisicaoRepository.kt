package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicoesCumResponseDTO
import school.sptech.projetotfg.dto.RequisicoesGraficoDiarioDTO
import school.sptech.projetotfg.dto.RequisicoesGraficoSemanalDTO
import school.sptech.projetotfg.dto.RequisicoesReqResponseDTO
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface RequisicaoRepository:JpaRepository<Requisicoes, Long> {

    @Query("SELECT COUNT(r) FROM Requisicoes r WHERE r.situacao.id = 5 AND r.dataCriacao >= :dataLimite")
    fun getQuantidadeRequisicoesNegadasUltimos30Dias(@Param("dataLimite") dataLimite: LocalDateTime): Long?

    @Query("SELECT COUNT(r) FROM Requisicoes r WHERE r.dataCriacao >= :dataLimite")
    fun getQuantidadeTotalRequisicoesUltimos30Dias(@Param("dataLimite") dataLimite: LocalDateTime): Long?

    @Query("CALL consulta_requisicoes_semanal_por_mes(:mes, :ano)", nativeQuery = true)
    fun getGraficoSemanal(@Param("mes") mes: Int, @Param("ano") ano: Int): List<Array<Any>>

    @Query("CALL consulta_requisicoes_por_semana(:ano, :mes, :semana)", nativeQuery = true)
    fun getGraficoDiarioPorSemana(
        @Param("mes") mes: Int,
        @Param("ano") ano: Int,
        @Param("semana") semana: Int
    ): List<Array<Any>>


}