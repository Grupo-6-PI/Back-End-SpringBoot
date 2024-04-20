package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TipoIdentificador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoIdentificador:Int,
    @field:Max(50) var tipo:String,
    @ManyToOne var situacao: Situacao
) {
}