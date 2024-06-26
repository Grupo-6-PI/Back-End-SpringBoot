package school.sptech.projetotfg.dto

import java.time.LocalDate

data class AtividadeResponseDTO(
    val id:Long,
    val nome:String,
    val tipo:String,
    val comeco:LocalDate,
    val descricao:String
)