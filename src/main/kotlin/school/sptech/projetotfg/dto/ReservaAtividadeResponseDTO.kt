package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.atividades.ReservaAtividade

class ReservaAtividadeResponseDTO {

    var domingo:MutableList<ReservaAtividade> = mutableListOf()
    var segunda:MutableList<ReservaAtividade> = mutableListOf()
    var terca:MutableList<ReservaAtividade> = mutableListOf()
    var quarta:MutableList<ReservaAtividade> = mutableListOf()
    var quinta:MutableList<ReservaAtividade> = mutableListOf()
    var sexta:MutableList<ReservaAtividade> = mutableListOf()
    var sabado:MutableList<ReservaAtividade> = mutableListOf()

}