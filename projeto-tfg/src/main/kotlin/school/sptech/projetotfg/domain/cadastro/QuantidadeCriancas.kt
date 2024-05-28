package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.jetbrains.annotations.NotNull
import school.sptech.projetotfg.domain.cadastro.Familia
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class QuantidadeCriancas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idQuantidadeCrianças:Long = 0,
    @field:NotNull private var quantidade:Long,
    @field:OneToOne private var familia: Familia,
    @field:ManyToOne private var situacao: Situacao,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String
) {
    fun getId():Long{
        return idQuantidadeCrianças
    }
    fun setId(novoId:Long){
        idQuantidadeCrianças = novoId
    }
    fun getQuantidade():Long{
        return quantidade
    }
    fun setQuantidade(novaQuantidade: Long){
        quantidade = novaQuantidade
    }
    fun getFamilia(): Familia {
        return familia
    }
    fun setFamilia(novaFamilia: Familia){
        familia = novaFamilia
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
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}