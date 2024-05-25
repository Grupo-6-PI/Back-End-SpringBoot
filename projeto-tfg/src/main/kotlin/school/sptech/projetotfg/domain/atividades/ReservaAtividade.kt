package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class ReservaAtividade(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idReservaAtividade: Int,
    @ManyToOne private var calendario: Calendario,
    @ManyToOne private var atividade: Atividade,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String
) {
    fun getId():Int{
        return idReservaAtividade
    }
    fun setId(novoId:Int){
        idReservaAtividade = novoId
    }
    fun getCalendario():Calendario{
        return calendario
    }
    fun setCalendario(novoCalendario:Calendario){
        calendario = novoCalendario
    }
    fun getAtividade():Atividade{
        return atividade
    }
    fun setAtividade(novaAtividade:Atividade){
        atividade = novaAtividade
    }
    fun setDataCriacao(novaDataCriacao:LocalDateTime){
        dataCriacao = novaDataCriacao
    }
    fun getDataUltimaAtualizacao():LocalDateTime{
        return dataUltimaAtualizacao
    }
    fun setDataUltimaAtualizacao(dataDeAtualizacao:LocalDateTime){
        dataUltimaAtualizacao = dataDeAtualizacao
    }
    fun getEmailModificador():String{
        return emailModificador
    }
    fun setEmailModificador(emailDeModificacao:String){
        emailModificador = emailDeModificacao
    }
}