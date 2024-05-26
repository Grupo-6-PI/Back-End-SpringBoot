package school.sptech.projetotfg.dto

import jakarta.validation.constraints.NotBlank

data class IdentificadorDTO(
    @field:NotBlank val tipo: String,
    @field:NotBlank val numero: String
) {
}