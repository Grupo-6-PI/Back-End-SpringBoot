package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDate

@Entity
class Acesso(
    @field: Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idAcesso: Int = 0,
    @field: NotNull @field: NotBlank
    private var dataAcesso: LocalDate,
    @field: NotNull @field: NotBlank @ManyToOne
    private var situacao: Situacao,
    @field: NotNull @field: NotBlank @ManyToOne
    private var usuario: Usuario
){
    fun getId():Int{
        return idAcesso
    }
    fun setId(novoId:Int){
        idAcesso = novoId
    }
    fun getDataAcesso():LocalDate{
        return dataAcesso
    }
    fun setDataAcesso(novaDataAcesso:LocalDate){
        dataAcesso = novaDataAcesso
    }
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
    fun getUsuario():Usuario{
        return usuario
    }
    fun setUsuario(novoUsuario: Usuario){
        usuario = novoUsuario
    }
}