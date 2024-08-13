package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class Bairro(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:NotBlank @field:Max(100) private var nome:String? = null,
    @field:ManyToOne private var cidade: Cidade? = null
) {
}