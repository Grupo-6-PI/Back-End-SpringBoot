package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.*

@Entity
class InformacoesAdicionais (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idInformacoesAdicionais:Long = 0,
    @field:CPF private var cpf:String,
    @field:Past private var dataNascimento:LocalDate,
    @field:OneToOne private var endereco: Endereco,
    @field:OneToOne private var familia: Familia,
    @field:OneToOne private var identificador: Identificador,
    @field:OneToOne private var contato: Contato,
    @field:ManyToOne private var situacao: Situacao,
    @field:PastOrPresent private var dataCriacao:LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime,
    @field:Email @field:Size(max = 100)
    private var emailModificador:String
){
    fun getId():Long{
        return idInformacoesAdicionais
    }
    fun setId(novoId:Long){
        idInformacoesAdicionais = novoId
    }
    fun getCpf():String{
        return cpf
    }
    fun setCpf(novoCpf:String){
        cpf = novoCpf
    }
    fun getDataNascimento():LocalDate{
        return dataNascimento
    }
    fun setDataNascimento(novaDataNascimento:LocalDate){
        dataNascimento = novaDataNascimento
    }
    fun getEndereco(): Endereco {
        return endereco
    }
    fun setEndereco(novoEndereco: Endereco){
        endereco = novoEndereco
    }
    fun getFamilia(): Familia {
        return familia
    }
    fun setFamilia(novaFamilia: Familia){
        familia = novaFamilia
    }
    fun getIdentificador(): Identificador {
        return identificador
    }
    fun setIdentificador(novoIdentificador: Identificador){
        identificador = novoIdentificador
    }
    fun getContato(): Contato {
        return contato
    }
    fun setContato(novoContato: Contato){
        contato = novoContato
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
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