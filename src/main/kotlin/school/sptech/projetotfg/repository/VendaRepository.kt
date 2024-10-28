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
    ): List<Venda>

    @Query("SELECT v.id, v.quantidade, v.valor, v.email_modificador, c.ano, c.mes_numeracao, c.dia_numeracao\n" +
            "FROM venda v\n" +
            "JOIN calendario c ON v.calendario_id = c.id\n" +
            "WHERE DATE(CONCAT(c.ano, '-', c.mes_numeracao, '-', c.dia_numeracao)) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);\n")
    fun getAllD30(): Optional<List<Venda>>
}
