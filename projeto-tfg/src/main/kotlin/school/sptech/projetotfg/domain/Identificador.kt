package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Identificador (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idIdentificador:Int,
    @field:Max(40) var numeracao:String,
    @ManyToOne var tipoIdentificador:TipoIdentificador,
    @ManyToOne var situacao: Situacao
){
}