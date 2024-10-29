package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Identificador (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var numeracao:String?,
    @field:ManyToOne private var tipoIdentificador: TipoIdentificador?,
    @field:ManyToOne private var situacao: Situacao?
){

    fun getId(): Long?{
        return id
    }

    fun setId(new:Long?){
        this.id = new
    }

    fun getNumeracao(): String?{
        return numeracao
    }

    fun setNumeracao(new: String?){
        this.numeracao = new
    }

    fun getTipoIdentificador(): TipoIdentificador?{
        return tipoIdentificador
    }

    fun setTipoIdentificador(new: TipoIdentificador?){
        this.tipoIdentificador = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

}