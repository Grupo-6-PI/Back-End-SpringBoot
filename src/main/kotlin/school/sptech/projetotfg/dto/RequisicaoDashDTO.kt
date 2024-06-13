package school.sptech.projetotfg.dto

data class RequisicaoDashDTO(
    var cesta_req:MutableList<Int>,
    var cesta_cum:MutableList<Int>,
    var vestuario_req:MutableList<Int>,
    var vestuario_cum:MutableList<Int>,
    var saude_req:MutableList<Int>,
    var saude_cum:MutableList<Int>,
    var outro_req:MutableList<Int>,
    var outro_cum:MutableList<Int>,
)