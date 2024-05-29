package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.Situacao


@Entity
class Endereco(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idEndereco:Long = 0,
    @field:Max(100) private var logradouro:String,
    @field:Positive private var numero: Long,
    @field:Size(min = 8, max = 8) private var cep:String,
    //tamanho 8 supõe que não será armazenado o traço "-"
    @field:ManyToOne private var bairro: Bairro,
    @field:ManyToOne private var situacao: Situacao
){
    fun getId():Long{
        return idEndereco
    }
    fun setId(novoId:Long){
        idEndereco = novoId
    }
    fun getLogradouro():String{
        return logradouro
    }
    fun setLogradouro(novoLogradouro:String){
        logradouro = novoLogradouro
    }
    fun getNumero():Long{
        return numero
    }
    fun setNumero(novoNumero:Long){
        numero = novoNumero
    }
    fun getCep():String{
        return cep
    }
    fun setCep(novoCep:String){
        cep = novoCep
    }
    fun getBairro(): Bairro {
        return bairro
    }
    fun setBairro(novoBairro: Bairro){
        bairro = novoBairro
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}