package school.sptech.projetotfg.dto

data class RequisicoesGraficoDiarioDTO(
    val diaNumeracao: Int,
    val diaNomeacao: String,
    val cestasPedidas: Int,
    val cestasCumpridas: Int,
    val vestuarioPedidas: Int,
    val vestuarioCumpridas: Int,
    val saudePedidas: Int,
    val saudeCumpridas: Int,
    val outrosPedidas: Int,
    val outrosCumpridas: Int
)