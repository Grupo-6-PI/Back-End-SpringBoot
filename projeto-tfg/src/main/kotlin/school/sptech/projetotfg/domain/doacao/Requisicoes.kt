package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.cadastro.Usuario
import java.time.LocalDateTime

@Entity
class Requisicoes (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idRequisicao: Long = 0,
    @field:ManyToOne private var assuntoRequisicao: AssuntoRequisicao,
    @field:ManyToOne private var nivelUrgencia: NivelUrgencia,
    @field:PastOrPresent private var dataAbertura:LocalDateTime,
    @field:PastOrPresent private var dataFechamento:LocalDateTime,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String,
    @field:ManyToOne private var usuario: Usuario
) {
    fun getId():Long{
        return idRequisicao
    }
    fun setId(novoId:Long){
        idRequisicao = novoId
    }
    fun getAssunto():AssuntoRequisicao{
        return assuntoRequisicao
    }
    fun setAssunto(novoAssunto:AssuntoRequisicao){
        assuntoRequisicao = novoAssunto
    }
    fun getNivel():NivelUrgencia{
        return nivelUrgencia
    }
    fun setNivel(novoNivel:NivelUrgencia){
        nivelUrgencia = novoNivel
    }
    fun getDataAbertura():LocalDateTime{
        return dataAbertura
    }
    fun setDataAbertura(novaDataAbertura: LocalDateTime){
        dataAbertura = novaDataAbertura
    }
    fun getDataFechamento():LocalDateTime{
        return dataFechamento
    }
    fun setDataFechamento(novaDataFechamento: LocalDateTime){
        dataFechamento = novaDataFechamento
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
    fun getUsuario():Usuario{
        return usuario
    }
    fun setUsuario(novoUsuario: Usuario){
        usuario = novoUsuario
    }
}