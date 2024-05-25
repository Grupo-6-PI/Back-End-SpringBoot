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
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idAno: Int,
    @field:NotBlank @field:Max(60) private var ano:String
) {
    fun getId():Int{
        return idAno
    }
    fun setId(novoId:Int){
        idAno = novoId
    }
    fun getAno():String{
        return ano
    }
    fun setAno(novoAno:String){
        ano = novoAno
    }
}