package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import java.time.LocalDate
import java.util.*

interface VendaRepository : JpaRepository<Venda, Long> {
    @Query(
        """
        SELECT v FROM Venda v
        JOIN v.calendario c
        WHERE v.emailModificador = :email
        AND c.ano = :ano
        AND c.mesNumeracao = :mes
        AND c.diaNumeracao = :dia
    """
    )
    fun findByEmailAndData(
        @Param("email") email: String,
        @Param("ano") ano: Int,
        @Param("mes") mes: Int,
        @Param("dia") dia: Int
    ): MutableList<Venda>

    @Query(
        value = """
        SELECT v.id,v.quantidade,v.valor,v.categoria_id,v.calendario_id,v.email_modificador
        FROM venda AS v
        JOIN calendario AS c ON v.calendario_id = c.id
        WHERE DATE(CONCAT(c.ano, '-', LPAD(c.mes_numeracao, 2, '0'), '-', LPAD(c.dia_numeracao, 2, '0'))) >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
    """,
        nativeQuery = true
    )
    fun getAllD30(): List<Venda>

    @Query(
        """
        SELECT v FROM Venda v
        WHERE v.calendario.ano = :ano
          AND v.calendario.mesNumeracao BETWEEN :mesInicio AND :mesFim
          AND v.calendario.diaNumeracao BETWEEN :diaInicio AND :diaFim
    """
    )
    fun findVendasByDataInterval(
        @Param("ano") ano: Int,
        @Param("mesInicio") mesInicio: Int,
        @Param("mesFim") mesFim: Int,
        @Param("diaInicio") diaInicio: Int,
        @Param("diaFim") diaFim: Int
    ): List<Venda>

    @Query(
        """
        SELECT v
        FROM Venda v
        JOIN v.calendario c
        WHERE 
            (c.ano > :anoInicio OR (c.ano = :anoInicio AND c.mesNumeracao > :mesInicio) 
             OR (c.ano = :anoInicio AND c.mesNumeracao = :mesInicio AND c.diaNumeracao >= :diaInicio))
            AND 
            (c.ano < :anoFim OR (c.ano = :anoFim AND c.mesNumeracao < :mesFim) 
             OR (c.ano = :anoFim AND c.mesNumeracao = :mesFim AND c.diaNumeracao <= :diaFim))
        """
    )
    fun findVendasUltimos30Dias(
        @Param("anoInicio") anoInicio: Int,
        @Param("mesInicio") mesInicio: Int,
        @Param("diaInicio") diaInicio: Int,
        @Param("anoFim") anoFim: Int,
        @Param("mesFim") mesFim: Int,
        @Param("diaFim") diaFim: Int
    ): List<Venda>


}