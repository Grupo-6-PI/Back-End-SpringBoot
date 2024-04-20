package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity

class TipoContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoContato:Int,
    @field:Max(30) var tipoContato:String,
    @ManyToOne var situacao: Situacao
){
}