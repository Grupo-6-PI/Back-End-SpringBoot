package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Past
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate

@Entity
class Crianca(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)  var idCriancas:Long = 0,
    @field:Max(60) private var genero:String,
    @field:Past private var dataNascimento:LocalDate,
    @field:ManyToOne private var quantidadeCriancas: QuantidadeCriancas,
    @field:ManyToOne private var tamanhoRoupa: TamanhoRoupa,
    @field:ManyToOne private var tamanhoCalcado: TamanhoCalcado,
    @field:ManyToOne private var situacao: Situacao
) {
    fun getId():Long{
        return idCriancas
    }
    fun setId(novoId:Long){
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
    fun getQuantidadeCriancas(): QuantidadeCriancas {
        return quantidadeCriancas
    }
    fun setQuantidadeCriancas(novaQuantidadeCriancas: QuantidadeCriancas){
        quantidadeCriancas = novaQuantidadeCriancas
    }
    fun getTamanhoRoupa(): TamanhoRoupa {
        return tamanhoRoupa
    }
    fun senTamanhoRoupa(novoTamanhoRoupa: TamanhoRoupa){
        tamanhoRoupa = novoTamanhoRoupa
    }
    fun getTamanhoCalcado(): TamanhoCalcado {
        return tamanhoCalcado
    }
    fun setTamanhoCalcado(novoTamanhoCalcado: TamanhoCalcado){
        tamanhoCalcado = novoTamanhoCalcado
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}