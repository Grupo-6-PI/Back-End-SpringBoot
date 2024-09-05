package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class TipoAtividade(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:NotBlank @field:Max(100) private var tipo:String?
) {

    fun getId():Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getTipo():String?{
        return tipo
    }

    fun setTipo(new:String?){
        this.tipo = new
    }

}