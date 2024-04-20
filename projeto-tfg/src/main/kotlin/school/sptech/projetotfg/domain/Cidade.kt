package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
@Entity
class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCidade:Int,
    @field:Max(100) var nome:String
) {
}