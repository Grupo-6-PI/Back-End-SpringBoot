package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
@Entity
class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCidade:Long = 0,
    @field:Max(100) private var nome:String
) {
    fun getId():Long{
        return idCidade
    }
    fun setId(novoId:Long){
        idCidade = novoId
    }
    fun getNome():String{
        return nome
    }
    fun setNome(novoNome:String){
        nome = novoNome
    }
}