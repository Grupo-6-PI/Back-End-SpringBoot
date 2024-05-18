package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class Bairro(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idBairro:Int,
    @field:NotBlank @field:Max(100) private var nome:String,
    @ManyToOne private var cidade:Cidade
) {
    fun getId():Int{
        return idBairro
    }
    fun setId(novoId:Int){
        idBairro = novoId
    }
    fun getNome():String{
        return nome
    }
    fun setNome(novoNome:String){
        nome = novoNome
    }
    fun getCidade():Cidade{
        return cidade
    }
}