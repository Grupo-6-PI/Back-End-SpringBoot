package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Permissao(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idPermissao:Int = 0,
    @field:Max(30) private var permissao:String
) {
    fun getId():Int{
        return idPermissao
    }
    fun setId(novoId:Int){
        idPermissao = novoId
    }
    fun getPermissao():String{
        return permissao
    }
    fun setPermissao(novaPermissao:String){
        permissao = novaPermissao
    }
}