package school.sptech.projetotfg.dto

import java.time.LocalDate
import java.time.LocalTime

data class AtividadeResponseDTO(
    val id:Long?,
    val nome:String?,
    val tipo:String?,
    val comeco:LocalTime?,
    val descricao:String?
)