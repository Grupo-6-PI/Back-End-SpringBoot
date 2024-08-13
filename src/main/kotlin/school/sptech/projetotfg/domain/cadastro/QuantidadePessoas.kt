package school.sptech.projetotfg.domain.cadastro


import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Positive private var minimo: Long? = null,
    @field:Positive private var maximo: Long? = null,
    @field:ManyToOne private var situacao: Situacao? = null
    ) {
}