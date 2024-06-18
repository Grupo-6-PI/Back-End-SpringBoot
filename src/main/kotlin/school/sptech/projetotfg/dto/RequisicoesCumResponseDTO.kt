package school.sptech.projetotfg.dto

class RequisicoesCumResponseDTO(
    var cesta_cum:MutableList<Int> = MutableList(4){0},
    var vestuario_cum:MutableList<Int> = MutableList(4){0},
    var saude_cum:MutableList<Int> = MutableList(4){0},
    var outro_cum:MutableList<Int> = MutableList(4){0},
) {
}