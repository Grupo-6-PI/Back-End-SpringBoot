package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idGrupoPermissao:Int,
    @ManyToOne private var permissao:Permissao,
    @ManyToOne private var nivelAcesso:NivelAcesso
){
    fun getId():Int{
        return idGrupoPermissao
    }
    fun setId(novoId:Int){
        idGrupoPermissao = novoId
    }
    fun getPermissao():Permissao{
        return permissao
    }
    fun setPermissao(novaPermissao:Permissao){
        permissao = novaPermissao
    }
    fun getNivelAcesso():NivelAcesso{
        return nivelAcesso
    }
    fun setNivelAcesso(novoNivelAcesso:NivelAcesso){
        nivelAcesso = novoNivelAcesso
    }

}