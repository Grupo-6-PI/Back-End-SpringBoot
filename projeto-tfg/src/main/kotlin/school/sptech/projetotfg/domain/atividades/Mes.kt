package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Mes(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idMes: Long = 0,
    @field:NotBlank @field:Max(60) private var mes:String
) {
    fun getId():Long{
        return idMes
    }
    fun setId(novoId:Long){
        idMes = novoId
    }
    fun getMes():String{
        return mes
    }
    fun setMes(novoMes:String){
        mes = novoMes
    }
}