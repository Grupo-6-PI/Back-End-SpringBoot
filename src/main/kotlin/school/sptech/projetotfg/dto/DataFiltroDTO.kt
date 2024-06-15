package school.sptech.projetotfg.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class DataFiltroDTO(
    @field:Positive val ano: Long,
    @field:Positive @field:Min(1) @field:Max(12) val mesNumeracao: Int,
    @field:Positive @field:Min(1) @field:Max(31) val diaNumeracao: Int
)
