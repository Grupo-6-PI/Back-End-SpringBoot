package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity

class TipoContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(30) private var tipo:String?,
    @field:ManyToOne private var situacao: Situacao?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getTipo(): String?{
        return tipo
    }

    fun setTipo(new: String?){
        this.tipo = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

}