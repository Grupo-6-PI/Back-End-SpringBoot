package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class DiaSemana(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idDiaSemana: Long = 0,
    @field:NotBlank @field:Max(60) private var diaSemana:String
) {
    fun getId():Long{
        return idDiaSemana
    }
    fun setId(novoId:Long){
        idDiaSemana = novoId
    }
    fun getDiaSemana():String{
        return diaSemana
    }
    fun setDiaSemana(novoDiaSemana:String){
        diaSemana = novoDiaSemana
    }
}