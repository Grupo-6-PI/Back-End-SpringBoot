package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Familia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idFamilia:Int,
    @OneToOne private var quantidadePessoas: QuantidadePessoas,
    @OneToOne private var pessoaDeficiencia: PessoaDeficiencia,
    @OneToOne private var urgenciaFamiliar: UrgenciaFamiliar,
    @OneToOne private var rendaFamiliar: RendaFamiliar,
    @ManyToOne private var situacao: Situacao,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String
) {
    fun getId():Int{
        return idFamilia
    }
    fun setId(novoId:Int){
        idFamilia = novoId
    }
    fun getQuatidadePessoas(): QuantidadePessoas {
        return quantidadePessoas
    }
    fun setQuantidadePessoas(novaQUantidade: QuantidadePessoas){
        quantidadePessoas = novaQUantidade
    }
    fun getPessoaDeficiencia(): PessoaDeficiencia {
        return pessoaDeficiencia
    }
    fun setPessoaDeficiencia(novaPessoaDeficiencia: PessoaDeficiencia){
        pessoaDeficiencia = novaPessoaDeficiencia
    }
    fun getUrgenciaFamiliar(): UrgenciaFamiliar {
        return urgenciaFamiliar
    }
    fun setUrgenciaFamiliar(novaUrgenciaFamiliar: UrgenciaFamiliar){
        urgenciaFamiliar = novaUrgenciaFamiliar
    }
    fun getRendaFamiliar(): RendaFamiliar {
        return rendaFamiliar
    }
    fun setRendaFamiliar(novaRendaFamiliar: RendaFamiliar){
        rendaFamiliar = novaRendaFamiliar
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