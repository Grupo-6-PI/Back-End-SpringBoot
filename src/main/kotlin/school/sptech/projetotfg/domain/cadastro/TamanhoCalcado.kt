package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoCalcado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var tamanho:Int?
) {

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getTamanho(): Int?{
        return tamanho
    }

    fun setTamanho(tamanho: Int?){
        this.tamanho = tamanho
    }

}