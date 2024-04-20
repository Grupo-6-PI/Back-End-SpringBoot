package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TipoDoacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoDoacao:Int,
    @field:Max(100) var doacao:String,
    @ManyToOne var situacao:Situacao
) {
}