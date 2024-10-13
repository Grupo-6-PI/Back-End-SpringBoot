package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.cadastro.Familia
import school.sptech.projetotfg.domain.cadastro.TamanhoCalcado
import school.sptech.projetotfg.domain.cadastro.TamanhoRoupa
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate

class DependenteInputDTO(
    var id:Long?,
    var dataNascimento:LocalDate?,
    var deficiente:Boolean?,
    var tamanhoRoupa:TamanhoRoupa?,
    var tamanhoCalcado:TamanhoCalcado?,
    var situacao:Situacao?,
    var familia:Familia?
) {
}