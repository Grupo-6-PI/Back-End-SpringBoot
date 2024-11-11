package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import java.time.LocalDate
import java.util.*

interface VendaRepository : JpaRepository<Venda, Long> {
    @Query("""
        SELECT v FROM Venda v
        JOIN v.calendario c
        WHERE v.emailModificador = :email
        AND c.ano = :ano
        AND c.mesNumeracao = :mes
        AND c.diaNumeracao = :dia
    """)
    fun findByEmailAndData(
        @Param("email") email: String,
        @Param("ano") ano: Int,
        @Param("mes") mes: Int,
        @Param("dia") dia: Int
    ): MutableList<Venda>

    @Query(
        value = """
    SELECT * 
    FROM venda v
    JOIN calendario c ON v.calendario_id = c.id
    WHERE STR_TO_DATE(CONCAT(c.ano, '-', c.mes_numeracao, '-', c.dia_numeracao), '%Y-%m-%d') >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
    """,
        nativeQuery = true
    )
    fun getAllD30(): List<Venda>

    @Query("SELECT v FROM Venda v WHERE v.calendario.ano = :ano AND v.calendario.mesNumeracao BETWEEN :mesInicio AND :mesFim AND v.calendario.diaNumeracao BETWEEN :diaInicio AND :diaFim")
    fun findVendasByDataInterval(
        @Param("ano") ano: Int,
        @Param("mesInicio") mesInicio: Int,
        @Param("mesFim") mesFim: Int,
        @Param("diaInicio") diaInicio: Int,
        @Param("diaFim") diaFim: Int
    ): List<Venda>
}
