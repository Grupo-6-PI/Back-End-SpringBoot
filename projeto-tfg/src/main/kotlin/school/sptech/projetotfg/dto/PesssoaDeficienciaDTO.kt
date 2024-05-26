package school.sptech.projetotfg.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class PessoaDeficienciaDTO(
    val verificacao: Int,
    val deficienciaNome: String, // Nome da deficiência
    val dataCriacao: LocalDateTime,
    val dataUltimaAtualizacao: LocalDateTime,
    val emailModificador: String
) {
}