package school.sptech.projetotfg.dto

class RequisicaoDashDTO(
    var cesta_req:MutableList<Int> = MutableList(4){0},
    var cesta_cum:MutableList<Int> = MutableList(4){0},
    var vestuario_req:MutableList<Int> = MutableList(4){0},
    var vestuario_cum:MutableList<Int> = MutableList(4){0},
    var saude_req:MutableList<Int> = MutableList(4){0},
    var saude_cum:MutableList<Int> = MutableList(4){0},
    var outro_req:MutableList<Int> = MutableList(4){0},
    var outro_cum:MutableList<Int> = MutableList(4){0},
)