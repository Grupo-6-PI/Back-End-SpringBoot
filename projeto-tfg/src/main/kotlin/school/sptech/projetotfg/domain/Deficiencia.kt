package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Deficiencia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idDeficiencia:Int,
    @field:NotBlank @field:Max(150) var nomeDeficiencia:String
) {
}