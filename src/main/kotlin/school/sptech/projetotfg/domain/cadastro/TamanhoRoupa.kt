package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoRoupa(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var tamanho:String?
) {

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getTamanho(): String?{
        return tamanho
    }

    fun setTamanho(tamanho: String?){
        this.tamanho = tamanho
    }

}