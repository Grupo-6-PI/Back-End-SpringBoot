package school.sptech.projetotfg.dto

import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.cadastro.Endereco
import java.time.LocalDateTime
import java.time.LocalTime

data class AtividadeDTO(
    @field:Size(max = 100) val nome: String,
    @field:PastOrPresent val horaComeco: LocalTime,
    @field:PastOrPresent val horaFinal: LocalTime,
    @field:NotBlank @field:Size(max = 150) val descricao: String,
    val tipoAtividadeId: Long,
    @field:Email @field:Size(max = 150) val emailModificador: String,
    var filtrodto: CalendarioFiltroDTO,
    var endereco: EnderecoInputAtividadeDTO
)
