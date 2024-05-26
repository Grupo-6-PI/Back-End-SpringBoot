package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.gerenciamento.Situacao


@Entity
class Endereco (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idEndereco:Int = 0,
    @field:Max(100) private var logradouro:String,
    @field:Positive private var numero: Int,
    @field:Size(min = 8, max = 8) private var cep:String,
    //tamanho 8 supõe que não será armazenado o traço "-"
    @ManyToOne private var bairro: Bairro,
    @ManyToOne private var situacao: Situacao
){
    fun getId():Int{
        return idEndereco
    }
    fun setId(novoId:Int){
        idEndereco = novoId
    }
    fun getLogradouro():String{
        return logradouro
    }
    fun setLogradouro(novoLogradouro:String){
        logradouro = novoLogradouro
    }
    fun getNumero():Int{
        return numero
    }
    fun setNumero(novoNumero:Int){
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