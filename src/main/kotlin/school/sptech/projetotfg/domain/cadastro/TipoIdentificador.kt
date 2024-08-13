package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class TipoIdentificador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Max(50) private var tipo:String? = null,
    @field:ManyToOne private var situacao: Situacao? = null
) {
}