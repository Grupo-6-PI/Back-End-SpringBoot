package school.sptech.projetotfg.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class EnderecoDTO(
    @field:NotBlank val logradouro: String,
    @field:Positive val numero: Long,
    @field:Size(min = 8, max = 8) val cep: String,
    @field:NotBlank val bairro: String,
    @field:NotBlank val cidade: String
) {
}