package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.*
import java.time.LocalDateTime

@Entity
class Atividade (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idAtividade: Int,
    @field:PastOrPresent private var comeco:LocalDateTime,
    @field:PastOrPresent private var final:LocalDateTime,
    @field:NotBlank @field:Max(150) private var descricao:String,
    @ManyToOne private var tipoAtividade: TipoAtividade,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String
){
    fun getId():Int{
        return idAtividade
    }
    fun setId(novoId:Int){
        idAtividade = novoId
    }
    fun getComeco():LocalDateTime{
        return comeco
    }
    fun setComeco(novoComeco:LocalDateTime){
        comeco = novoComeco
    }
    fun getFinal():LocalDateTime{
        return final
    }
    fun setFinal(novoFinal:LocalDateTime){
        final = novoFinal
    }
    fun getDescricao():String{
        return descricao
    }
    fun setDescricao(novaDescricao:String){
        descricao = novaDescricao
    }
    fun getTipoAtividade():TipoAtividade{
        return tipoAtividade
    }
    fun setTipoAtividade(novoTipoAtividade: TipoAtividade){
        tipoAtividade = novoTipoAtividade
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