package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class FaixaEtaria(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long = 0,
    @field:NotBlank @field:Max(150) private var faixa:String
) {
}