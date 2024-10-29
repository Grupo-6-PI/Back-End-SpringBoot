package school.sptech.projetotfg.dto

data class RequisicaoResquestDTO(
    var assuntoId:Long,
    var descricao:String,
    var usuarioId:Long,
    var emailModificador:String,
    var data: CalendarioFiltroDTO?
)