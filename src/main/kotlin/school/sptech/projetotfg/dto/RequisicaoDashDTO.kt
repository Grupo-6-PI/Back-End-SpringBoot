package school.sptech.projetotfg.dto

class RequisicaoDashDTO(
    var cesta_req:MutableList<Int> = mutableListOf(),
    var cesta_cum:MutableList<Int> = mutableListOf(),
    var vestuario_req:MutableList<Int> = mutableListOf(),
    var vestuario_cum:MutableList<Int> = mutableListOf(),
    var saude_req:MutableList<Int> = mutableListOf(),
    var saude_cum:MutableList<Int> = mutableListOf(),
    var outro_req:MutableList<Int> = mutableListOf(),
    var outro_cum:MutableList<Int> = mutableListOf(),
)