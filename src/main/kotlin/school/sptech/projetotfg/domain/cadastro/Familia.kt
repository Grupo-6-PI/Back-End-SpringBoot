package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Familia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:OneToOne private var quantidadePessoas: QuantidadePessoas?,
    @field:OneToOne private var rendaFamiliar: RendaFamiliar?,
    @field:ManyToOne private var situacao: Situacao?,
    @field:PastOrPresent private var dataCriacao: LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?
) {

    @OneToMany(mappedBy = "familia")
    var dependentes:MutableList<Dependente> = mutableListOf()

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long){
        this.id = new
    }

    fun getQuantidadePessoas(): QuantidadePessoas?{
        return quantidadePessoas
    }

    fun setQuantidadePessoas(new: QuantidadePessoas){
        this.quantidadePessoas = new
    }

    fun getRendaFamiliar(): RendaFamiliar?{
        return rendaFamiliar
    }

    fun setRendaFamiliar(new: RendaFamiliar){
        this.rendaFamiliar = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao){
        this.situacao = new
    }

    fun getDataCriacao(): LocalDateTime?{
        return dataCriacao
    }

    fun setDataCriacao(new: LocalDateTime){
        this.dataCriacao = new
    }

    fun getDataUltimaAtualizacao(): LocalDateTime?{
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(new: LocalDateTime){
        this.dataUltimaAtualizacao = new
    }

    fun getEmailModificador(): String?{
        return emailModificador
    }

    fun setEmailModificador(new:String){
        this.emailModificador = new
    }

}