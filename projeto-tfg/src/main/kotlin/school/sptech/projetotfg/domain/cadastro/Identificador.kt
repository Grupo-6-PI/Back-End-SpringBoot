package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Identificador (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idIdentificador:Long = 0,
    @field:Max(40) private var numeracao:String,
    @field:ManyToOne private var tipoIdentificador: TipoIdentificador,
    @field:ManyToOne private var situacao: Situacao
){
    fun getId():Long{
        return idIdentificador
    }
    fun setId(novoId:Long){
        idIdentificador = novoId
    }
    fun getNumeracao():String{
        return numeracao
    }
    fun setNumeracao(novaNumeracao:String){
        numeracao = novaNumeracao
    }
    fun getTipoIdentificador(): TipoIdentificador {
        return tipoIdentificador
    }
    fun setTipoIdentificador(novoTipoIdentificador: TipoIdentificador){
        tipoIdentificador = novoTipoIdentificador
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}