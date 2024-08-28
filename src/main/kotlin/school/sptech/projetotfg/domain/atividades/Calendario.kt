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
class Calendario(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:Positive private var ano: Int?,
    @field:Positive @field:Min(1) @field:Max(2) private var mesNumeracao: Int?,
    @field:NotBlank private var mesNomeacao:String?,
    @field:Positive @field:Min(1) @field:Max(2) private var diaNumeracao: Int?,
    @field:NotBlank private var diaNomeacao: String?
){

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getAno():Int?{
        return ano
    }

    fun setAno(new: Int?){
        this.ano = new
    }

    fun getMesNumeracao():Int?{
        return mesNumeracao
    }

    fun setMesNumeracao(new: Int?){
        this.mesNumeracao = new
    }

    fun getMesNomeacao():String?{
        return mesNomeacao
    }

    fun setMesNomeacao(new: String?){
        this.mesNomeacao = new
    }

    fun getDiaNumeracao():Int?{
        return diaNumeracao
    }

    fun setDiaNumeracao(new: Int?){
        this.diaNumeracao = new
    }

    fun getDiaNomeacao():String?{
        return diaNomeacao
    }

    fun setDiaNomeacao(new: String){
        this.diaNomeacao = new
    }



}