package school.sptech.projetotfg.domain.atividades

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@Entity
class Repeticao (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:Positive private var quantidade:Int?,
    @field:NotBlank @field:Max(45) private var escolhaRepeticao:String?,
    @field:ManyToOne private var disponibilidadeVoluntario:DisponibilidadeVoluntario?
){

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getQuantidade():Int?{
        return quantidade
    }

    fun setQuantidade(new:Int?){
        this.quantidade = new
    }

    fun getEscolhaRepeticao():String?{
        return escolhaRepeticao
    }

    fun setEscolhaRepeticao(new:String?){
        this.escolhaRepeticao = new
    }

    fun getDisponibilidadeVoluntario():DisponibilidadeVoluntario?{
        return disponibilidadeVoluntario
    }

    fun setDisponibilidadeVoluntario(new:DisponibilidadeVoluntario?){
        this.disponibilidadeVoluntario = new
    }

}