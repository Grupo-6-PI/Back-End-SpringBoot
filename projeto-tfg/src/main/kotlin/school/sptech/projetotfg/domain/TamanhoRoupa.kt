package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoRoupa(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTamanhoRoupa:Int,
    @field:Max(10) var tamanho:String
) {
}