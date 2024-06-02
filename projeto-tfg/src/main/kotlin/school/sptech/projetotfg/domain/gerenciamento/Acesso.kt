package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.cadastro.Usuario
import java.time.LocalDate

@Entity
class Acesso(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field: NotNull @field: NotBlank private var dataAcesso: LocalDate,
    @field:NotNull @field:NotBlank @field:ManyToOne private var situacao: Situacao,
    @field: NotNull @field: NotBlank @field:ManyToOne private var usuario: Usuario
){
}