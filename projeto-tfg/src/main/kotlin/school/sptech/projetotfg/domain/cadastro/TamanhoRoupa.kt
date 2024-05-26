package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoRoupa(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTamanhoRoupa:Int = 0,
    @field:Max(10) private var tamanho:String
) {
    fun getId():Int{
        return idTamanhoRoupa
    }
    fun setId(novoId:Int){
        idTamanhoRoupa = novoId
    }
    fun getTamanho():String{
        return tamanho
    }
    fun setTamanho(novoTamanho:String){
        tamanho = novoTamanho
    }

}