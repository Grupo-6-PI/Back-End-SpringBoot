package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Familia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:OneToOne private var quantidadePessoas: QuantidadePessoas? = null,
    @field:OneToOne private var rendaFamiliar: RendaFamiliar? = null,
    @field:ManyToOne private var situacao: Situacao? = null,
    @field:PastOrPresent private var dataCriacao: LocalDateTime? = null,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime? = null,
    @field:Email @field:Size(max = 150) private var emailModificador:String? = null
) {
}