package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import java.time.LocalDate

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
}
