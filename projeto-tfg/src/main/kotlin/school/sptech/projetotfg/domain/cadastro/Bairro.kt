package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class Bairro(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idBairro:Long = 0 ,
    @field:NotBlank @field:Max(100) private var nome:String,
    @field:ManyToOne private var cidade: Cidade
) {
    fun getId():Long{
        return idBairro
    }
    fun setId(novoId:Long){
        idBairro = novoId
    }
    fun getNome():String{
        return nome
    }
    fun setNome(novoNome:String){
        nome = novoNome
    }
    fun getCidade(): Cidade {
        return cidade
    }
}