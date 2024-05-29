package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository

class RequisicoesService (
    val requisicaoRepository: RequisicaoRepository,
    val mapper: ModelMapper
){
    fun listarRequisicoes():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll()
        if(!listaRequisicoes.isEmpty()){
            val listaDto:MutableList<RequisicoesDoacaoResponseDTO> = mutableListOf()
            listaDto.forEachIndexed{index, atividade ->
                mapper.map(listaRequisicoes, RequisicoesDoacaoResponseDTO::class.java)
            }
            return listaDto
        }
        throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }
}