package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class RendaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Min(0) private var renda:Double?,
    @field:ManyToOne private var situacao: Situacao?
) {

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getRenda(): Double?{
        return renda
    }

    fun setRenda(new: Double?){
        this.renda = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

}