package school.sptech.projetotfg.domain

import jakarta.persistence.*

@Entity
data class AuthUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_usuario: Int = 0,
    val email: String,
    val senha: String
)
