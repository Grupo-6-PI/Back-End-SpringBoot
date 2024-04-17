package school.sptech.projetotfg.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCidade:Int,
    @field:Maxvar nome:String
) {
}