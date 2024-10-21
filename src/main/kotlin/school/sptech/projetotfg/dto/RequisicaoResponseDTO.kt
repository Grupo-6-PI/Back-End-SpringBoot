package school.sptech.projetotfg.dto

import java.time.LocalDate

data class RequisicaoResponseDTO(
    val id:Long,
    val numeracao:Int,
    val solicitante:String,
    val CPF:String,
    val dataNasc:LocalDate,
    val endereco:String,
    val familiaOrigem:String,
    val quantidadePessoas:Int,
    val possuiCrianca:Boolean,
    val possuiPCD: Boolean,
    val rendaFamiliar:Double,
    val tipoRequisicao:String,
    val descricao:String
)