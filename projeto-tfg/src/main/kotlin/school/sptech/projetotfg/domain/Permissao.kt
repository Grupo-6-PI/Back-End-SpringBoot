package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Permissao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idPermissao:Int,
    @field:Max(30) var permissao:String
) {

}