package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.*
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
class Atividade (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = 0,
    @field:NotBlank @field:Max(100) private var nome:String?,
    @field:PastOrPresent private var horaComeco: LocalTime?,
    @field:PastOrPresent private var horaFinal:LocalTime?,
    @field:NotBlank @field:Max(150) private var descricao:String?,
    @field:ManyToOne private var tipoAtividade: TipoAtividade?,
    @field:PastOrPresent private var dataCriacao: LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String?
){

    fun getId():Long?{
        return id
    }

    fun setId(id:Long){
        this.id = id
    }

    fun getNome():String?{
        return nome
    }

    fun setNome(nome: String){
        this.nome = nome
    }

    fun getHoraComeco():LocalTime?{
        return horaComeco
    }

    fun setHoraComeco(horaComeco: LocalTime){
        this.horaComeco = horaComeco
    }

    fun getHoraFinal():LocalTime?{
        return horaFinal
    }

    fun setHoraFinal(horaFinal: LocalTime){
        this.horaFinal = horaFinal
    }

    fun getDescricao():String?{
        return descricao
    }

    fun setDescricao(descricao:String){
        this.descricao = descricao
    }

    fun getTipoAtividade(): TipoAtividade? {
        return tipoAtividade
    }

    fun setTipoAtividade(tipoAtividade: TipoAtividade){
        this.tipoAtividade = tipoAtividade
    }

    fun getDataCriacao(): LocalDateTime?{
        return dataCriacao
    }

    fun setDataCriacao(dataCriacao: LocalDateTime){
        this.dataCriacao = dataCriacao
    }

    fun getEmailModificador():String?{
        return emailModificador
    }

    fun setEmailModificador(emailModificador:String){
        this.emailModificador = emailModificador
    }

    fun getDataUltimaAtualizacao(): LocalDateTime?{
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(dataUltima: LocalDateTime){
        this.dataUltimaAtualizacao = dataUltima
    }

}