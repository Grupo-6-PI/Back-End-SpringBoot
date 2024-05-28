package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class NivelUrgencia (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idNivelUrgencia: Long = 0,
    @field:NotBlank @field:Max(60) private var nivel:String
)
    {
    fun getId():Long{
        return idNivelUrgencia
    }
    fun setId(novoId:Long){
        idNivelUrgencia = novoId
    }
    fun getAssunto():String{
        return nivel
    }
    fun setAssunto(novoNivel:String){
        nivel = novoNivel
    }
}