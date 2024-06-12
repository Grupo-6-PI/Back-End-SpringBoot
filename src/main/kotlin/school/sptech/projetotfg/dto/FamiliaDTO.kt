package school.sptech.projetotfg.dto

import jakarta.validation.constraints.NotBlank

data class FamiliaDTO(
    @field:NotBlank val nomeResponsavel: String,
    @field:NotBlank val parentesco: String
) {
}