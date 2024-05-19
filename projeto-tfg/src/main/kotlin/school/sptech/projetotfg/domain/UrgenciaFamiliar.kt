package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class UrgenciaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idUrgenciaFamiliar:Int,
    @field:Max(150) private var descricao:String,
    @ManyToOne private var situacao:Situacao
) {
    fun getId():Int{
        return idUrgenciaFamiliar
    }
    fun setId(novoId:Int){
        idUrgenciaFamiliar = novoId
    }
    fun getDescricao():String{
        return descricao
    }
    fun setDescricao(novaDescricao:String){
        descricao = novaDescricao
    }
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSitucao:Situacao){
        situacao = novaSitucao
    }
}