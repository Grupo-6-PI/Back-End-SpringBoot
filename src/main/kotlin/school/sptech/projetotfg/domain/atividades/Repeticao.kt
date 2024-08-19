package school.sptech.projetotfg.domain.atividades

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@Entity
class Repeticao (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:Positive private var quantidade:Long?,
    @field:NotBlank @field:Max(45) private var escolhaRepeticao:String?,
    @field:ManyToOne private var disponibilidadeVoluntario:DisponibilidadeUsuario?
){
}