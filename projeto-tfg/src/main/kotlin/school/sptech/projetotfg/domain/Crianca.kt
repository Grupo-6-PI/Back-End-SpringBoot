package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Past
import java.util.Date

@Entity
class Crianca(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idCriancas:Int,
    @field:Max(60) var genero:String,
    @field:Past var dataNascimento:Date,
    @ManyToOne var quantidadeCriancas: QuantidadeCriancas,
    @ManyToOne var tamanhoRoupa:TamanhoRoupa,
    @ManyToOne var tamanhoCalcado:TamanhoCalcado,
    @ManyToOne var situacao: Situacao
) {
}