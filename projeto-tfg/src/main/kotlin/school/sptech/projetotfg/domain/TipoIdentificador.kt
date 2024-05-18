package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TipoIdentificador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoIdentificador:Int,
    @field:Max(50) private var tipo:String,
    @ManyToOne private var situacao: Situacao
) {
    fun getId():Int{
        return idTipoIdentificador
    }
    fun setId(novoId:Int){
        idTipoIdentificador = novoId
    }
    fun getTipo():String{
        return tipo
    }
    fun setTipo(novoTipo:String){
        tipo = novoTipo
    }
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}