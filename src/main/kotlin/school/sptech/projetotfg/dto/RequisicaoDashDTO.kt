package school.sptech.projetotfg.dto

class RequisicaoDashDTO(
    var cesta_req:MutableList<Long> = mutableListOf(),
    var cesta_cum:MutableList<Long> = mutableListOf(),
    var vestuario_req:MutableList<Long> = mutableListOf(),
    var vestuario_cum:MutableList<Long> = mutableListOf(),
    var saude_req:MutableList<Long> = mutableListOf(),
    var saude_cum:MutableList<Long> = mutableListOf(),
    var outro_req:MutableList<Long> = mutableListOf(),
    var outro_cum:MutableList<Long> = mutableListOf(),
)