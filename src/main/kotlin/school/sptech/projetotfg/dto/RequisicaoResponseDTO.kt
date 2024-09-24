package school.sptech.projetotfg.dto

data class RequisicaoResponseDTO(
    val numeracao:Int,
    val solicitante:String,
    val CPF:String,
    val endereco:String,
    val familiaOrigem:String,
    val quantidadePessoas:Int,
    val possuiCrianca:Boolean,
    val possuiPCD: Boolean,
    val rendaFamiliar:Double,
    val tipoRequisicao:String,
    val descricao:String
)