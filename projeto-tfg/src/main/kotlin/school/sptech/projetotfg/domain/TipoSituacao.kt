package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max

@Entity
class TipoSituacao (
    private @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoSituacao:Int,
    private @field:Max(20) var tipo:String
){



}