package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(

    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @field:Max(100) var situacao: String,
    @field:ManyToOne var tipoSituacaoId: TipoSituacao
){

}

