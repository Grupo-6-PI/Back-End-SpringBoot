package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Ano(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idAno: Long = 0,
    @field:NotBlank @field:Max(60) private var ano:String
) {
    fun getId():Long{
        return idAno
    }
    fun setId(novoId:Long){
        idAno = novoId
    }
    fun getAno():String{
        return ano
    }
    fun setAno(novoAno:String){
        ano = novoAno
    }
}