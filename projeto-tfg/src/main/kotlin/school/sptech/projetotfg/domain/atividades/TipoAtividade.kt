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
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoAtividade: Int = 0,
    @field:NotBlank @field:Max(100) private var tipo:String
) {
    fun getId():Int{
        return idTipoAtividade
    }
    fun setId(novoId:Int){
        idTipoAtividade = novoId
    }
    fun getTipo():String{
        return tipo
    }
    fun setTipo(novoTipo:String){
        tipo = novoTipo
    }
}