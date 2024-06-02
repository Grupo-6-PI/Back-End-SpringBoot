package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
@Entity
class Cidade(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long = 0,
    @field:Max(100) private var nome:String,
    @ManyToOne private var estado: Estado
) {
}