package school.sptech.projetotfg.dto

import jakarta.validation.constraints.*

data class ReservaAtividadeDTO(
    @field:NotNull val calendarioId: Long,
    @field:NotNull val atividadeId: Long,
    @field:Email @field:Size(max = 150) val emailModificador: String
)
