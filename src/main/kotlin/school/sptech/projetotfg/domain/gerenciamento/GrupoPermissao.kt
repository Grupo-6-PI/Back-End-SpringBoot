package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:ManyToOne private var permissao: Permissao?,
    @field:ManyToOne private var nivelAcesso: NivelAcesso?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getPermissao():Permissao?{
        return permissao
    }

    fun setPermissao(new: Permissao?){
        this.permissao = new
    }

    fun getNivelAcesso():NivelAcesso?{
        return nivelAcesso
    }

    fun setNivelAcesso(new:NivelAcesso?){
        this.nivelAcesso = new
    }

}