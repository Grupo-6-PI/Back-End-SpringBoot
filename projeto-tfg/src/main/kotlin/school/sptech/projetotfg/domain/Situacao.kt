package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idSituacao:Int,
    @field:Max(100) var situacao:String,
    @ManyToOne var tipoSituacao: TipoSituacao
){





}