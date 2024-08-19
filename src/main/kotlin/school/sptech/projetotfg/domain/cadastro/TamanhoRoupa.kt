package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class TamanhoRoupa(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(10) private var tamanho:String?,
    @ManyToOne private var faixaEtaria: FaixaEtaria?
) {
}