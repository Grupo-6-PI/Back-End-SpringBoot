package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idGrupoPermissao:Long = 0,
    @field:ManyToOne private var permissao: Permissao,
    @field:ManyToOne private var nivelAcesso: NivelAcesso
){
    fun getId():Long{
        return idGrupoPermissao
    }
    fun setId(novoId:Long){
        idGrupoPermissao = novoId
    }
    fun getPermissao(): Permissao {
        return permissao
    }
    fun setPermissao(novaPermissao: Permissao){
        permissao = novaPermissao
    }
    fun getNivelAcesso(): NivelAcesso {
        return nivelAcesso
    }
    fun setNivelAcesso(novoNivelAcesso: NivelAcesso){
        nivelAcesso = novoNivelAcesso
    }

}