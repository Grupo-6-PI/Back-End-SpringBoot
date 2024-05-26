package school.sptech.projetotfg.dto

import jakarta.validation.constraints.NotBlank

data class ContatoDTO(
    @field:NotBlank val telefone: String,
    @field:NotBlank val celular: String
) {
}