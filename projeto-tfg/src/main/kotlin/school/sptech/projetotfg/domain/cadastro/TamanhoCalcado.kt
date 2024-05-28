package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoCalcado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTamanhoCalcado:Long = 0,
    @field:Max(10) private var tamanho:String
) {
    fun getId():Long{
        return idTamanhoCalcado
    }
    fun setId(novoId:Long){
        idTamanhoCalcado = novoId
    }
    fun getTamanho():String{
        return tamanho
    }
    fun setTamanho(novoTamanho:String){
        tamanho = novoTamanho
    }
}