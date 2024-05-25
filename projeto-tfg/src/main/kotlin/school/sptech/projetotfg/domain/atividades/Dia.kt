package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@Entity
class Dia(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idDia: Int,
    @field:Positive @field:Max(2) @field:Min(1) private var dia:Int
) {
    fun getId():Int{
        return idDia
    }
    fun setId(novoId:Int){
        idDia = novoId
    }
    fun getDia():Int{
        return dia
    }
    fun setDia(novoDia:Int){
        dia = novoDia
    }
}