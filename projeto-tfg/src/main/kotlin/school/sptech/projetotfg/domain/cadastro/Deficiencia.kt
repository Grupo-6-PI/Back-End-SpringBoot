package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Deficiencia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idDeficiencia:Long = 0,
    @field:NotBlank @field:Max(150) private var nomeDeficiencia:String
) {
    fun getId():Long{
        return idDeficiencia
    }
    fun setId(novoId:Long){
        idDeficiencia = novoId
    }
    fun getNomeDeficiencia():String{
        return nomeDeficiencia
    }
    fun setNomeDeficiencia(novoNomeDeficiencia:String){
        nomeDeficiencia = novoNomeDeficiencia
    }
}