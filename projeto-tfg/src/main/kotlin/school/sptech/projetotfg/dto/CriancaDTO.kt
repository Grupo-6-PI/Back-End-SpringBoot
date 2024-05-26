package school.sptech.projetotfg.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate


data class CriancaDTO(
    @field:NotBlank val nome: String,
    @field:Past val dataNascimento: LocalDate
) {
}