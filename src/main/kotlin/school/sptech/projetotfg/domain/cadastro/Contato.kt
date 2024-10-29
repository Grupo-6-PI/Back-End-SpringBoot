package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class Contato(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var informacoesContato:String?,
    private var dataCriacao:LocalDateTime?,
    private var dataUltimaAtualizacao:LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?,
    @ManyToOne private var tipoContato:TipoContato?,
    @ManyToOne private var informacoesAdicionais: InformacoesAdicionais?
) {

    fun getId(): Long? {
        return id
    }

    fun setId(new: Long?) {
        this.id = new
    }

    fun setInformacoesContato(new: String?) {
        this.informacoesContato = new
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

    fun getEmailModificador(): String? {
        return emailModificador
    }

    fun setEmailModificador(new: String?) {
        this.emailModificador = new
    }

    fun getTipoContato(): TipoContato? {
        return tipoContato
    }

    fun setTipoContato(new:TipoContato?){
        this.tipoContato = new
    }

    fun getInformacoesAdicionais(): InformacoesAdicionais? {
        return informacoesAdicionais
    }

    fun setInformacoesAdicionais(new: InformacoesAdicionais?) {
        this.informacoesAdicionais = new
    }

}