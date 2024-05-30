package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class PerguntasFrequentes(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:NotBlank @field:Max(45) private var pergunta:String,
    @field:ManyToOne private var opcoesAtendimento: OpcoesAtendimento
) {
}