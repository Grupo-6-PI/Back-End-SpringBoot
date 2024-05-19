package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoCalcado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTamanhoCalcado:Int,
    @field:Max(10) private var tamanho:String
) {
    fun getId():Int{
        return idTamanhoCalcado
    }
    fun setId(novoId:Int){
        idTamanhoCalcado = novoId
    }
    fun getTamanho():String{
        return tamanho
    }
    fun setTamanho(novoTamanho:String){
        tamanho = novoTamanho
    }
}