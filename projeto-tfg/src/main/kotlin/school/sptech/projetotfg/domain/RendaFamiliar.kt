package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Min

@Entity
class RendaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idRendaFamiliar:Int,
    @field:Min(0) var renda:Double,
    @ManyToOne var situacao: Situacao
) {
}