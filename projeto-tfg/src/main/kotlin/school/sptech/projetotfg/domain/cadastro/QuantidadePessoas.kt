package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idQuantidadePessoas:Long = 0,
    @field:NotNull private var minimo: Int,
    @field:NotNull private var maximo: Int,
    @field:ManyToOne private var situacao: Situacao
    ) {
}