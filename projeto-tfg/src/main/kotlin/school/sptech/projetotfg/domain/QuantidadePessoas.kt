package school.sptech.projetotfg.domain

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idQuantidadePessoas:Int,
    @field:NotNull var minimo:Int,
    @field:NotNull var maximo:Int,
    @ManyToOne var situacao: Situacao
    ) {
}