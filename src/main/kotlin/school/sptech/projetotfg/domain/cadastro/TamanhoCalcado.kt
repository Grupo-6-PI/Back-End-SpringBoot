package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoCalcado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(10) private var tamanho:String?,
    @ManyToOne var faixaEtaria: FaixaEtaria?
) {

    fun getId(): Long{
        return id!!
    }

    fun setId(new: Long){
        this.id = new
    }

    fun getTamanho(): String{
        return tamanho!!
    }

    fun setTamanho(tamanho: String){
        this.tamanho = tamanho
    }

    fun getFaixaEtaria(): FaixaEtaria{
        return faixaEtaria!!
    }

    fun setFaixaEtaria(new: FaixaEtaria){
        this.faixaEtaria = new
    }

}