package school.sptech.projetotfg.dto

data class RequisicoesGraficoDiarioDTO(
    val diaNumeracao: Int,     // Representa o número do dia (1 a 7)
    val diaNomeacao: String,   // Nome do dia da semana
    val cestasPedidas: Int,    // Cestas pedidas
    val cestasCumpridas: Int,  // Cestas cumpridas
    val vestuarioPedidas: Int, // Vestuário pedido
    val vestuarioCumpridas: Int, // Vestuário cumprido
    val saudePedidas: Int,     // Saúde pedida
    val saudeCumpridas: Int,   // Saúde cumprida
    val outrosPedidas: Int,    // Outros pedidos
    val outrosCumpridas: Int  // Outros cumpridos
)