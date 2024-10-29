package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class InformacoesAdicionais (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var cpf:String?,
    @field:Past private var dataNascimento:LocalDate?,
    @field:OneToOne private var endereco: Endereco?,
    @field:OneToOne private var familia: Familia?,
    @field:OneToOne private var identificador: Identificador?,
    @field:ManyToOne private var situacao: Situacao?,
    private var dataCriacao:LocalDateTime?,
    private var dataUltimaAtualizacao:LocalDateTime?,
    @field:Email @field:Size(max = 100) private var emailModificador:String?
){

    @field:OneToMany(mappedBy = "informacoesAdicionais")
    var contatos:MutableList<Contato> = mutableListOf()

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getCpf(): String?{
        return cpf
    }

    fun setCpf(new: String?){
        this.cpf = new
    }

    fun getDataNascimento(): LocalDate?{
        return dataNascimento
    }

    fun setDataNascimento(new: LocalDate?){
        this.dataNascimento = new
    }

    fun getEndereco(): Endereco?{
        return endereco
    }

    fun setEndereco(new: Endereco?){
        this.endereco = new
    }

    fun getFamilia(): Familia?{
        return familia
    }

    fun setFamilia(new: Familia?){
        this.familia = new
    }

    fun getIdentificador(): Identificador?{
        return identificador
    }

    fun setIdentificador(new: Identificador?){
        this.identificador = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

    fun getDataCriacao(): LocalDateTime?{
        return dataCriacao
    }

    fun setDataCriacao(new: LocalDateTime?){
        this.dataCriacao = new
    }

    fun getDataUltimaAtualizacao(): LocalDateTime?{
        return dataUltimaAtualizacao
    }

    fun setDataUltimaAtualizacao(new: LocalDateTime?){
        this.dataUltimaAtualizacao = new
    }

    fun getEmailModificador(): String?{
        return emailModificador
    }

    fun setEmailModificador(new: String?){
        this.emailModificador = new
    }

}