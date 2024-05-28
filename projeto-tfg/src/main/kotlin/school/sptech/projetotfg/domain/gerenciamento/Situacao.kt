package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(

    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idSituacao: Int = 0,
    @field:Max(100) var situacao: String,
    @ManyToOne @JoinColumn(name = "tipo_situacao_id_tipo_situacao") var tipoSituacao: TipoSituacao
){
    fun getId():Int{
        return idSituacao
    }
    fun setId(novoId:Int){
        idSituacao = novoId
    }
}

