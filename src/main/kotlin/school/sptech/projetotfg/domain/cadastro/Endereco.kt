package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.Situacao


@Entity
class Endereco(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var logradouro:String?,
    @field:Positive private var numero: Int?,
    private var cep:String?,
    @field:ManyToOne private var bairro: Bairro?,
    @field:ManyToOne private var situacao: Situacao?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getLogradouro(): String?{
        return logradouro
    }

    fun setLogradouro(new: String?){
        this.logradouro = new
    }

    fun getNumero(): Int?{
        return numero
    }

    fun setNumero(new: Int?){
        this.numero = new
    }

    fun getCep(): String?{
        return cep
    }

    fun setCep(new: String?){
        this.cep = new
    }

    fun getBairro(): Bairro?{
        return bairro
    }

    fun setBairro(new: Bairro?){
        this.bairro = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

}