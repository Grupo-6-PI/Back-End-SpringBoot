package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import java.time.LocalDateTime

data class VendaResponseDTO(
    var id: Long?,
    var quantidade: Int?,
    var valor: Double?,
    var categoria: CategoriaDTO,
    var calendario: Calendario?,
    var dataModificacao: LocalDateTime?
)
