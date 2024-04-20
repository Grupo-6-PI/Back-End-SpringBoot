package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TipoSituacao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoSituacao:Int,
    @field:Max(20) var tipo:String
){



}