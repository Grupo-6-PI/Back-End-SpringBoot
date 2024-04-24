package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDate

@Entity
class Acesso(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_acesso: Int = 0,
    @field: NotNull @field: NotBlank
    val data_acesso: LocalDate,
    @field: NotNull @field: NotBlank @ManyToOne
    val situacao: Situacao,
    @field: NotNull @field: NotBlank @ManyToOne
    val usuario: Usuario
)
