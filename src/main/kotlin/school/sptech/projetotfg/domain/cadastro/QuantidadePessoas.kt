package school.sptech.projetotfg.domain.cadastro


import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Positive private var minimo: Int?,
    @field:Positive private var maximo: Int?,
    @field:ManyToOne private var situacao: Situacao?
) {

    fun getId():Long?{
        return id
    }

    fun setId(new: Long){
        this.id = new
    }

    fun getMinimo(): Int?{
        return minimo
    }

    fun setMinimo(new: Int){
        this.minimo = new
    }

    fun getMaximo(): Int?{
        return maximo
    }

    fun setMaximo(new: Int){
        this.maximo = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao){
        this.situacao = new
    }

}