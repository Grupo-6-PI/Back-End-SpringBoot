package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity

class TipoContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoContato:Long = 0,
    @field:Max(30) private var tipoContato:String,
    @field:ManyToOne private var situacao: Situacao
){
    fun getId():Long{
        return idTipoContato
    }
    fun setId(novoId:Long){
        idTipoContato = novoId
    }
    fun getTipoContato():String{
        return tipoContato
    }
    fun setTipoContato(novoTipoContato:String){
        tipoContato = novoTipoContato
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}