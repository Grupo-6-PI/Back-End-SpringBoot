package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class Contato(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idContato:Int,
    @field:PastOrPresent private var dataCriacao:LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String
    ) {
    fun getId():Int{
        return idContato
    }
    fun setId(novoId:Int){
        idContato = novoId
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