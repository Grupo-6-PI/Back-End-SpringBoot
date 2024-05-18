package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Past
import java.time.LocalDate
import java.util.Date

@Entity
class Crianca(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idCriancas:Int,
    @field:Max(60) private var genero:String,
    @field:Past private var dataNascimento:LocalDate,
    @ManyToOne private var quantidadeCriancas: QuantidadeCriancas,
    @ManyToOne private var tamanhoRoupa:TamanhoRoupa,
    @ManyToOne private var tamanhoCalcado:TamanhoCalcado,
    @ManyToOne private var situacao: Situacao
) {
    fun getId():Int{
        return idCriancas
    }
    fun setId(novoId:Int){
        idCriancas = novoId
    }
    fun getGenero():String{
        return genero
    }
    fun setGenero(novoGenero:String){
        genero = novoGenero
    }
    fun getDataNascimento():LocalDate   {
        return dataNascimento
    }
    fun setDataNascimento(novaDataNascimento:LocalDate){
        dataNascimento = novaDataNascimento
    }
    fun getQuantidadeCriancas():QuantidadeCriancas{
        return quantidadeCriancas
    }
    fun setQuantidadeCriancas(novaQuantidadeCriancas: QuantidadeCriancas){
        quantidadeCriancas = novaQuantidadeCriancas
    }
    fun getTamanhoRoupa():TamanhoRoupa{
        return tamanhoRoupa
    }
    fun senTamanhoRoupa(novoTamanhoRoupa: TamanhoRoupa){
        tamanhoRoupa = novoTamanhoRoupa
    }
    fun getTamanhoCalcado():TamanhoCalcado{
        return tamanhoCalcado
    }
    fun setTamanhoCalcado(novoTamanhoCalcado: TamanhoCalcado){
        tamanhoCalcado = novoTamanhoCalcado
    }
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}