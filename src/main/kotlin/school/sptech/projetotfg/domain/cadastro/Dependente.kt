package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate

@Entity
class Dependente(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Max(60) private var genero:String? = null,
    @field:Past private var dataNascimento:LocalDate? = null,
    @field:NotBlank private var deficiente: Boolean? = null,
    @field:ManyToOne private var tamanhoRoupa: TamanhoRoupa? = null,
    @field:ManyToOne private var tamanhoCalcado: TamanhoCalcado? = null,
    @field:ManyToOne private var situacao: Situacao? = null,
    @field:ManyToOne private var familia: Familia? = null
) {
}