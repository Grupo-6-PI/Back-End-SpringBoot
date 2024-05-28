package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate

@Entity
class Acesso(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idAcesso: Long = 0,
    @field: NotNull @field: NotBlank
    private var dataAcesso: LocalDate,
    @field:NotNull @field:NotBlank @field:ManyToOne private var situacao: Situacao,
    @field: NotNull @field: NotBlank @field:ManyToOne
    private var usuario: Usuario
){
    fun getId():Long{
        return idAcesso
    }
    fun setId(novoId:Long){
        idAcesso = novoId
    }
    fun getDataAcesso():LocalDate{
        return dataAcesso
    }
    fun setDataAcesso(novaDataAcesso:LocalDate){
        dataAcesso = novaDataAcesso
    }
    fun getUsuario(): Usuario {
        return usuario
    }
    fun setUsuario(novoUsuario: Usuario){
        usuario = novoUsuario
    }

    fun getSituacao():Situacao{
        return situacao
    }

}