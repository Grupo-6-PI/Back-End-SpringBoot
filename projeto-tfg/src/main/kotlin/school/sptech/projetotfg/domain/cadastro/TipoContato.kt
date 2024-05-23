package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity

class TipoContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoContato:Int,
    @field:Max(30) private var tipoContato:String,
    @ManyToOne private var situacao: Situacao
){
    fun getId():Int{
        return idTipoContato
    }
    fun setId(novoId:Int){
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