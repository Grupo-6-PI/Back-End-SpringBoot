package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class AcessoUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_acesso: Int = 0,
    @field: NotNull @field: NotBlank
    val data_acesso: String,
    @field: NotNull @field: NotBlank
    val fk_situacao: String,
    @field: NotNull @field: NotBlank
    val senha: String
)
