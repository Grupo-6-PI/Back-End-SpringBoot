package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate

@Entity
class Dependente(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Past private var dataNascimento:LocalDate?,
    private var deficiente: Boolean?,
    @field:ManyToOne private var tamanhoRoupa: TamanhoRoupa?,
    @field:ManyToOne private var tamanhoCalcado: TamanhoCalcado?,
    @field:ManyToOne private var situacao: Situacao?,
    @field:ManyToOne private var familia: Familia?
) {

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getDataNascimento():LocalDate?{
        return dataNascimento
    }

    fun setDataNascimento(new: LocalDate?){
        this.dataNascimento = new
    }

    fun getDeficiente():Boolean?{
        return deficiente
    }

    fun setDeficiente(new: Boolean?){
        this.deficiente = new
    }

    fun getTamanhoRoupa():TamanhoRoupa?{
        return tamanhoRoupa
    }

    fun setTamanhoRoupa(new: TamanhoRoupa?){
        this.tamanhoRoupa = new
    }

    fun getTamanhoCalcado():TamanhoCalcado?{
        return tamanhoCalcado
    }

    fun setTamanhoCalcado(new: TamanhoCalcado?){
        this.tamanhoCalcado = new
    }

    fun getSituacao():Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

    fun getFamilia():Familia?{
        return familia
    }

    fun setFamilia(new: Familia?){
        this.familia = new
    }

}