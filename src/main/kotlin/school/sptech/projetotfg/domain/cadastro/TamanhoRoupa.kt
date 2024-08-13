package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoRoupa(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Max(10) private var tamanho:String? = null,
    @ManyToOne private var faixaEtaria: FaixaEtaria? = null
) {
}