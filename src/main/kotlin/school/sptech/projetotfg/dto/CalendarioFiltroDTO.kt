package school.sptech.projetotfg.dto

data class CalendarioFiltroDTO(
    val ano: Int,
    val mesNumeracao: Int,
    val diaNumeracao: Int,
    var diaNomeacao: String?,
)
