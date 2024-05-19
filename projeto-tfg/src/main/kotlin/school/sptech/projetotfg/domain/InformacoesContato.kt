package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import org.jetbrains.annotations.NotNull
import kotlin.math.log

@Entity
class InformacoesContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idInformacoesContato:Int,
    @field:Max(150) @field:NotNull private var contato:String,
    @ManyToOne private var tipoContato:TipoContato,
    @ManyToOne private var logContato:Contato,
    @ManyToOne private var situacao: Situacao,

){
    fun getId():Int{
        return idInformacoesContato
    }
    fun setId(novoId:Int){
        idInformacoesContato = novoId
    }
    fun getTipoContato():TipoContato{
        return tipoContato
    }
    fun setTipoContato(novoTipoContato:TipoContato){
        tipoContato = novoTipoContato
    }
    fun getLogContato():Contato{
        return logContato
    }
    fun setLogContato(novoContato: Contato){
        logContato = novoContato
    }
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}