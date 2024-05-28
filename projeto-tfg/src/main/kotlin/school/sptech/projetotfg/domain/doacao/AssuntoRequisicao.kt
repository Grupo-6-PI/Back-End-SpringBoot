package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class AssuntoRequisicao(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idAssuntoRequisicao: Long = 0,
    @field:NotBlank @field:Max(150) private var assunto:String
) {

    fun getId():Long{
        return idAssuntoRequisicao
    }
    fun setId(novoId:Long){
        idAssuntoRequisicao = novoId
    }
    fun getAssunto():String{
        return assunto
    }
    fun setAssunto(novoAssunto:String){
        assunto = novoAssunto
    }
}