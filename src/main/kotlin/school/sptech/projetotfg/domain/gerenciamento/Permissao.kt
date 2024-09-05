package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Permissao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(30) private var permissao:String?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getPermissao():String?{
        return permissao
    }

    fun setPermissao(permissao: String?){
        this.permissao = permissao
    }

}