package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.cadastro.Endereco
import java.time.LocalDateTime

@Entity
class ReservaAtividade(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:ManyToOne private var atividade: Atividade?,
    @field:PastOrPresent private var dataCriacao: LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador: String?,
    @field:ManyToOne private var calendario:Calendario?,
    @field:ManyToOne private var endereco:Endereco?
) {

    fun getId(): Long? {
        return id
    }

    fun setId(new: Long?) {
        this.id = new
    }

    fun getAtividade(): Atividade? {
        return atividade
    }

    fun setAtividade(new: Atividade?) {
        this.atividade = new
    }

    fun getDataCriacao(): LocalDateTime? {
        return dataCriacao
    }

    fun setDataCriacao(new: LocalDateTime?) {
        this.dataCriacao = new
    }

    fun getDataUltimaAtualizacao(): LocalDateTime? {
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(new: LocalDateTime?) {
        this.dataUltimaAtualizacao = new
    }

    fun getEmailModificador(): String? {
        return emailModificador
    }

    fun setEmailModificador(new: String?) {
        this.emailModificador = new
    }

    fun getCalendario(): Calendario? {
        return calendario
    }

    fun setCalendario(new: Calendario?) {
        this.calendario = new
    }

    fun getEndereco():Endereco?{
        return endereco
    }

    fun setEndereco(new:Endereco?){
        this.endereco = new
    }

}