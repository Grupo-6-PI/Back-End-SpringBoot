package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoCalcado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTamanhoCalcado:Int,
    @field:Max(10) var tamanho:String
) {
}