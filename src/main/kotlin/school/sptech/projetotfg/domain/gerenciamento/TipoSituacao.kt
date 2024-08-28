package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
class TipoSituacao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(60) private var tipo:String?
){

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getTipo(): String?{
        return tipo
    }

    fun setTipo(new: String?){
        this.tipo = new
    }

}