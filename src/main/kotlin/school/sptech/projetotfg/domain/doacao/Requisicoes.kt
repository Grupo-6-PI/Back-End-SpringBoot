package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Requisicoes (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:ManyToOne private var assuntoRequisicao: AssuntoRequisicao?,
    @field:PastOrPresent private var dataCriacao:LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?,
    @field:ManyToOne private var usuario: Usuario?,
    @field:ManyToOne private var situacao: Situacao?,
    @field:ManyToOne private var calendario: Calendario?,
    private var descricao:String?
) {

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getAssuntoRequisicao(): AssuntoRequisicao?{
        return assuntoRequisicao
    }

    fun setAssuntoRequisicao(new:AssuntoRequisicao?){
        this.assuntoRequisicao = new
    }

    fun getDataCriacao(): LocalDateTime?{
        return dataCriacao
    }

    fun setDataCriacao(new:LocalDateTime?){
        this.dataCriacao = new
    }

    fun getDataUltimaAtualizacao(): LocalDateTime?{
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(new:LocalDateTime?){
        this.dataUltimaAtualizacao = new
    }

    fun getEmailModificador(): String?{
        return emailModificador
    }

    fun setEmailModificador(new:String?){
        this.emailModificador = new
    }

    fun getUsuario(): Usuario?{
        return usuario
    }

    fun setUsuario(new:Usuario?){
        this.usuario = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new:Situacao?){
        this.situacao = new
    }

    fun getCalendario():Calendario?{
        return calendario
    }

    fun setCalendario(new:Calendario?){
        this.calendario = new
    }

    fun getDescricao():String?{
        return descricao
    }

    fun setDescricao(new : String?){
        this.descricao = new
    }

}