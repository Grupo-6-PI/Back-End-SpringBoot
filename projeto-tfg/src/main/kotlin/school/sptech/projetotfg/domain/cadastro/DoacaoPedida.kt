package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class DoacaoPedida(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idDoacaoPedida:Int = 0,
    @ManyToOne private var tipoDoacao: TipoDoacao,
    @ManyToOne private var familia: Familia,
    @ManyToOne private var situacao: Situacao,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String
) {
    fun getId():Int{
        return idDoacaoPedida
    }
    fun setId(novoId:Int){
        idDoacaoPedida = novoId
    }
    fun getTipoDoacao(): TipoDoacao {
        return tipoDoacao
    }
    fun setTipoDoacao(novoTipoDoacao: TipoDoacao){
        tipoDoacao = novoTipoDoacao
    }
    fun getFamilia(): Familia {
        return familia
    }
    fun setFamilia(novaFamilia: Familia){
        familia = novaFamilia
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