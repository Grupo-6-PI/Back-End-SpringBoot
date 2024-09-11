package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda

interface VendaRepository : JpaRepository<Venda, Long> {
}