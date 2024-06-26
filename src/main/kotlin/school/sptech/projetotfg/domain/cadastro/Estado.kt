package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
class Estado(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long = 0,
    @field:NotBlank @field:NotNull private var nome: String,
    @field:NotBlank @field:NotNull @field:Size(min = 2, max = 2) private var uf: String
) {
}