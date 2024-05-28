package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class NivelAcesso (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idNivelAcesso:Long = 0,
    @field:Max(60) private var apelido:String
){
    fun getId():Long{
        return idNivelAcesso
    }
    fun setId(novoId:Long){
        idNivelAcesso = novoId
    }
    fun getApelido():String{
        return apelido
    }
    fun setApelido(novoApelido:String){
        apelido = novoApelido
    }
}