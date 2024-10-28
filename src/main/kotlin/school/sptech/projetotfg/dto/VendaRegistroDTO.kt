package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria

data class VendaRegistroDTO(
    var id: Long?,
    var quantidade: Int?,
    var valor: Double?,
    var categoria: Categoria?,
    var emailModificador: String?,
    var calendario: CalendarioFiltroDTO?
)
