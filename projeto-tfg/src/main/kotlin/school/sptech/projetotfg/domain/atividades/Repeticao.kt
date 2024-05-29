package school.sptech.projetotfg.domain.atividades

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@Entity
class Repeticao (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:Positive private var quantidade:Long,
    @field:NotBlank @field:Max(45) private var escolhaRepeticao:String,
    @field:ManyToOne private var disponibilidadeVoluntario:Long
){
}