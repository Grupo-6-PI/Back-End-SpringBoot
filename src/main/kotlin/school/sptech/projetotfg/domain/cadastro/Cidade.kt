package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
@Entity
class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var nome:String?,
    @ManyToOne private var estado: Estado?
) {

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getNome():String?{
        return nome
    }

    fun setNome(new:String?){
        this.nome = new
    }

    fun getEstado(): Estado?{
        return estado
    }

    fun setEstado(new: Estado?){
        this.estado = new
    }

}