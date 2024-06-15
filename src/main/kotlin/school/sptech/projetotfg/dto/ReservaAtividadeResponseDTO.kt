package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.atividades.Atividade

class ReservaAtividadeResponseDTO {

    var domingo:MutableList<Atividade> = mutableListOf()
    var segunda:MutableList<Atividade> = mutableListOf()
    var terca:MutableList<Atividade> = mutableListOf()
    var quarta:MutableList<Atividade> = mutableListOf()
    var quinta:MutableList<Atividade> = mutableListOf()
    var sexta:MutableList<Atividade> = mutableListOf()
    var sabado:MutableList<Atividade> = mutableListOf()

}