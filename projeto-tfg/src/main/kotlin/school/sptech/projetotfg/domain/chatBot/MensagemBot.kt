package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class MensagemBot(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var tipoMensagem:TipoMensagem,
    @field:ManyToOne private var areasAtendimento: AreasAtendimento
) {
}