package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class UrgenciaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idUrgenciaFamiliar:Int,
    @field:Max(150) var descicao:String,
    @ManyToOne var situacao:Situacao
) {
}