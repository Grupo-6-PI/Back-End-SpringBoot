package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
class TipoSituacao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoSituacao:Int,
    @field:Max(60) private var tipo:String
){
    fun getId():Int{
        return idTipoSituacao
    }
    fun setId(novoId:Int){
        idTipoSituacao = novoId
    }
    fun getSituacao():String{
    return tipo
    }
    fun setStuacao(novaSituacao:String){
        tipo = novaSituacao
    }


}