package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Identificador (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Max(40) private var numeracao:String? = null,
    @field:ManyToOne private var tipoIdentificador: TipoIdentificador? = null,
    @field:ManyToOne private var situacao: Situacao? = null
){
}