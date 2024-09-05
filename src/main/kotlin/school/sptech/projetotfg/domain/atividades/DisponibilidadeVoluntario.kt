package school.sptech.projetotfg.domain.atividades

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.doacao.Requisicoes
import java.time.LocalDateTime

@Entity
class DisponibilidadeVoluntario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:ManyToOne private var requisicoes:Requisicoes?,
    @field:PastOrPresent private var dataCriacao: LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?,
    @field:PastOrPresent private var horaComeco:LocalDateTime?,
    @field:PastOrPresent private var horaFinal:LocalDateTime?,
    @field:ManyToOne private var calendario:Calendario?
){

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getRequisicoes():Requisicoes?{
        return requisicoes
    }

    fun setRequisicoes(new: Requisicoes?){
        this.requisicoes = new
    }

    fun getDataCriacao():LocalDateTime?{
        return dataCriacao
    }

    fun setDataCriacao(new: LocalDateTime?){
        this.dataCriacao = new
    }

    fun getDataUltimaAtualizacao():LocalDateTime?{
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(new: LocalDateTime?){
        this.dataUltimaAtualizacao = new
    }

    fun getEmailModificador():String?{
        return emailModificador
    }

    fun setEmailModificador(new:String?){
        this.emailModificador = new
    }

    fun getHoraComeco():LocalDateTime?{
        return horaComeco
    }

    fun setHoraComeco(new: LocalDateTime?){
        this.horaComeco = new
    }

    fun getHoraFinal():LocalDateTime?{
        return horaFinal
    }

    fun setHoraFinal(new: LocalDateTime?){
        this.horaFinal = new
    }

    fun getCalendario():Calendario?{
        return calendario
    }

    fun setCalendario(new: Calendario?){
        this.calendario = new
    }

}