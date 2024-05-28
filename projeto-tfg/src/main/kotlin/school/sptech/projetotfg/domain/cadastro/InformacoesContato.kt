package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import org.jetbrains.annotations.NotNull
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class InformacoesContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idInformacoesContato:Long = 0,
    @field:Max(150) @field:NotNull private var contato:String,
    @field:ManyToOne private var tipoContato: TipoContato,
    @field:ManyToOne private var logContato: Contato,
    @field:ManyToOne private var situacao: Situacao,

    ){
    fun getId():Long{
        return idInformacoesContato
    }
    fun setId(novoId:Long){
        idInformacoesContato = novoId
    }
    fun getTipoContato(): TipoContato {
        return tipoContato
    }
    fun setTipoContato(novoTipoContato: TipoContato){
        tipoContato = novoTipoContato
    }
    fun getLogContato(): Contato {
        return logContato
    }
    fun setLogContato(novoContato: Contato){
        logContato = novoContato
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}