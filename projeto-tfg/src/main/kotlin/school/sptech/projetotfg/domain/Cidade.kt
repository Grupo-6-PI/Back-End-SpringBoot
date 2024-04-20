package school.sptech.projetotfg.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max

class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCidade:Int,
    @field:Max(30) var nome:String
) {
}