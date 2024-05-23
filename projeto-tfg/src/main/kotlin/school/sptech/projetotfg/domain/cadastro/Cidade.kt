package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
@Entity
class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idCidade:Int,
    @field:Max(100) private var nome:String
) {
    fun getId():Int{
        return idCidade
    }
    fun setId(novoId:Int){
        idCidade = novoId
    }
    fun getNome():String{
        return nome
    }
    fun setNome(novoNome:String){
        nome = novoNome
    }
}