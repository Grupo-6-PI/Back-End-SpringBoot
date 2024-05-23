package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class NivelAcesso (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idNivelAcesso:Int,
    @field:Max(60) private var apelido:String
){
    fun getId():Int{
        return idNivelAcesso
    }
    fun setId(novoId:Int){
        idNivelAcesso = novoId
    }
    fun getApelido():String{
        return apelido
    }
    fun setApelido(novoApelido:String){
        apelido = novoApelido
    }
}