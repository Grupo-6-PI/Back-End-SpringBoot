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
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)  var id:Long = 0,
    @field:Max(60) private var genero:String,
    @field:Past private var dataNascimento:LocalDate,
    @field:NotBlank var deficiente: Boolean,
    @field:ManyToOne private var tamanhoRoupa: TamanhoRoupa,
    @field:ManyToOne private var tamanhoCalcado: TamanhoCalcado,
    @field:ManyToOne private var situacao: Situacao,
    @field:ManyToOne private var familia: Familia
) {
}