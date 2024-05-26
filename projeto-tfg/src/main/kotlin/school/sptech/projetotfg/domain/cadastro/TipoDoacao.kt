package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class TipoDoacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoDoacao:Int = 0,
    @field:Max(100) private var doacao:String,
    @ManyToOne private var situacao: Situacao
) {
    fun getId():Int{
        return idTipoDoacao
    }
    fun setId(novoId:Int){
        idTipoDoacao = novoId
    }
    fun getDoacao():String{
        return doacao
    }
    fun setDoacao(novaDoacao:String){
        doacao = novaDoacao
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }

}