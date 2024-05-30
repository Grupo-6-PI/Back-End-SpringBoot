package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.*

@Entity
class HistoricoConversa(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var entradaUsuario: EntradaUsuario,
    @field:OneToMany private var mensagemBot: MensagemBot
) {
}