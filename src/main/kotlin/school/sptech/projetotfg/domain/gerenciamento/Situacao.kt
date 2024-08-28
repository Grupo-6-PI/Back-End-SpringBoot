package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Situacao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(100) private var situacao:String?,
    @ManyToOne private var tipoSituacao: TipoSituacao?
){

    fun getId():Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getSituacao():String?{
        return situacao
    }

    fun setSituacao(new: String?){
        this.situacao = new
    }

    fun getTipoSituacao(): TipoSituacao? {
        return tipoSituacao
    }

    fun setTipoSituacao(new: TipoSituacao?){
        this.tipoSituacao = new
    }

}
