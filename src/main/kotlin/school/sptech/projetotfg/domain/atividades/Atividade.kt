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
class Atividade(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    private var nome:String?,
    private var horaComeco: LocalTime?,
    private var horaFinal: LocalTime?,
    private var descricao:String?,
    @field:ManyToOne private var tipoAtividade: TipoAtividade?,
    @field:PastOrPresent private var dataCriacao: LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?
){

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getNome():String?{
        return nome
    }

    fun setNome(new:String?){
        this.nome = new
    }

    fun getHoraComeco():LocalTime?{
        return horaComeco
    }

    fun setHoraComeco(new: LocalTime?){
        this.horaComeco = new
    }

    fun getHoraFinal():LocalTime?{
        return horaFinal
    }

    fun setHoraFinal(new: LocalTime?){
        this.horaFinal = new
    }

    fun getDescricao():String?{
        return descricao
    }

    fun setDescricao(new:String?){
        this.descricao = new
    }

    fun getTipoAtividade():TipoAtividade?{
        return tipoAtividade
    }

    fun setTipoAtividade(new: TipoAtividade?){
        this.tipoAtividade = new
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

}