package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class NivelAcesso (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idNivelAcesso:Int,
    @field:Max(60) var apelido:String
){

}