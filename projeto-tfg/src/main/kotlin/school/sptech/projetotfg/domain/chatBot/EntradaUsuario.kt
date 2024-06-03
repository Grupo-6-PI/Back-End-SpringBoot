package school.sptech.projetotfg.domain.chatBot

import jakarta.persistence.*
import school.sptech.projetotfg.domain.cadastro.Usuario

@Entity
class EntradaUsuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var usuario: Usuario
) {
}