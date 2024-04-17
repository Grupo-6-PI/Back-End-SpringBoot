package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max

@Entity
class TipoIdentificador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idTipoIdentificador:Int,
    @field:Max(50) var tipo:String,
    @ManyToOne var situacao: Situacao
) {
}