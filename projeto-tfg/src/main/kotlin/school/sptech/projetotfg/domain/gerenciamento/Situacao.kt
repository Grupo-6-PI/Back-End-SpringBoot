package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(

    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idSituacao: Long = 0,
    @field:Max(100) var situacao: String,
    @field:ManyToOne @field:JoinColumn(name = "tipo_situacao_id_tipo_situacao") var tipoSituacao: TipoSituacao
){
    fun getId():Long{
        return idSituacao
    }
    fun setId(novoId:Long){
        idSituacao = novoId
    }
}

