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
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:Positive private var ano: Long?,
    @field:Positive @field:Min(1) @field:Max(2) private var mesNumeracao: Int?,
    @field:NotBlank private var mesNomeacao:String?,
    @field:Positive @field:Min(1) @field:Max(2) private var diaNumeracao: Int?,
    @field:NotBlank private var diaNomeacao: String?
){

    fun getAno(): Long {
        return ano!!
    }

    fun setAno(ano: Long){
        this.ano = ano
    }

    fun getMesNomeacao(): String {
        return mesNomeacao!!
    }

    fun getDiaNomeacao(): String {
        return diaNomeacao!!
    }

    fun getMesNumeracao(): Int {
        return mesNumeracao!!
    }

    fun setMesNumeracao(numeracao: Int) {
        this.mesNumeracao = numeracao
    }

    fun getDiaNumeracao(): Int {
        return diaNumeracao!!
    }

    fun setDiaNumeracao(diaNumeracao: Int) {
        this.diaNumeracao = diaNumeracao
    }

    fun getId():Long{
        return id!!
    }

}