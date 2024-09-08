package school.sptech.projetotfg.domain.relatoriofinanceiro.dto

import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria

data class VendaRegistroDTO(
    val id: Long?,
    val quantidade: Int?,
    val valor: Double?,
    val categoriaId: Long,
    val emailModificador: String?,
    val calendarioId: Long
)
