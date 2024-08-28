package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class FaixaEtaria(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:NotBlank @field:Max(150) private var faixa:String?
) {

    fun getId(): Long ?{
        return id
    }

    fun setId(new: Long?) {
        this.id = new
    }

    fun getFaixa(): String ?{
        return faixa
    }

    fun setFaixa(new: String?) {
        this.faixa = new
    }

}