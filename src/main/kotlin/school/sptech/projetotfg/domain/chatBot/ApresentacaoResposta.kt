package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class ApresentacaoResposta(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:ManyToOne private var respostasPossiveis: RespostasPossiveis?,
    @field:ManyToOne private var perguntasFrequentes: PerguntasFrequentes?
) {
}