package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TipoSituacao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id_tipo_situacao:Int,
    @field:Max(60) var tipo:String
){



}