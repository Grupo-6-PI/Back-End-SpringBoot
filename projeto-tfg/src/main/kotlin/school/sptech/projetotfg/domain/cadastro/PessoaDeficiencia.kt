package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class PessoaDeficiencia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idPessoaDeficiencia:Long = 0,
    @field:Max(255) private var verificacao: Long, //255 é o valor maximo de TINYLong
    @field:ManyToOne private var deficiencia: Deficiencia,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String
    ) {
    fun getId():Long{
        return idPessoaDeficiencia
    }
    fun setId(novoId:Long){
        idPessoaDeficiencia = novoId
    }
    fun getVerificacao():Long{
        return verificacao
    }
    fun setVerificacao(novaVerificacao:Long){
        verificacao = novaVerificacao
    }
    fun getDeficiencia(): Deficiencia {
        return deficiencia
    }
    fun setDeficiencia(novaDeficiencia: Deficiencia){
        deficiencia = novaDeficiencia
    }
    fun getDataCriacao():LocalDateTime{
        return dataCriacao
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