package school.sptech.projetotfg.dto

import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.atividades.TipoAtividade
import java.time.LocalDateTime
import java.time.LocalTime

data class ReservaAtividadeDTO(
    val calendario: Calendario?,
    val atividade: LocalDateTime?,
    val emailModificador: String?
){

}
