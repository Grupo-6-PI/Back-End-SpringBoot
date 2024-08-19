package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.*

@Entity
class HistoricoConversa(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:ManyToOne private var entradaUsuario: EntradaUsuario?,
    @field:ManyToOne private var mensagemBot: MensagemBot?
){
}