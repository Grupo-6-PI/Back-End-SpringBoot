package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class NivelAcesso (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(60) private var apelido:String?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getApelido(): String?{
        return apelido
    }

    fun setApelido(new: String?){
        this.apelido = new
    }

}