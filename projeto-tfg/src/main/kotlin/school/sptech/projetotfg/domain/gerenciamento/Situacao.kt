package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idSituacao:Int,
    @field:Max(100) private var situacao:String,
    @ManyToOne private var tipoSituacao: TipoSituacao
){
    fun getId():Long{
        return idSituacao
    }
    fun setId(novoId:Long){
        idSituacao = novoId
    }
    fun getSituacao():String{
        return situacao
    }
    fun setSituacao(novaSituacao: String){
        situacao = novaSituacao
    }
    fun getTipoSituacao(): TipoSituacao {
        return tipoSituacao
    }
    fun setTipoSituacao(novoTipoSituacao: TipoSituacao){
        tipoSituacao = novoTipoSituacao
    }
}