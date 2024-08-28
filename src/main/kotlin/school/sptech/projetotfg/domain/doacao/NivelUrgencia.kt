package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class NivelUrgencia (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:NotBlank @field:Max(60) private var nivel:String?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getNivel(): String?{
        return nivel
    }

    fun setNivel(new: String?){
        this.nivel = new
    }

}