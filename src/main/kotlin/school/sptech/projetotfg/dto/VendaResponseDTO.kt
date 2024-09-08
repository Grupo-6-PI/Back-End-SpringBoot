package school.sptech.projetotfg.domain.relatoriofinanceiro.dto

import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import java.time.LocalDateTime

data class VendaResponseDTO(
    val quantidade: Int?,
    val valor: Double?,
    val categoria: Categoria?,
    val calendario: Calendario?,
    val dataModificacao: LocalDateTime?
)
