package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicaoDetalheResponseDTO
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository

@Service
class RequisicoesService (
    val requisicaoRepository: RequisicaoRepository,
    val mapper: ModelMapper
){
    fun listarRequisicoes():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll()

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesAceitas():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll() // criar com nova modelagem um método na repositpry que busca baseado na requisição ter sido aceita ou não

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesNegadas():List<RequisicoesDoacaoResponseDTO>{
        val listaRequisicoes = requisicaoRepository.findAll()//criar consulta das negadas

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesAbertas():List<RequisicoesDoacaoResponseDTO>{
        val listaRequisicoes = requisicaoRepository.findAll()//criar consulta das negadas

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun transformarListaEmDto(listaRequisicoes: List<Requisicoes>):List<RequisicoesDoacaoResponseDTO>{

        val listaDto:MutableList<RequisicoesDoacaoResponseDTO> = mutableListOf()
        listaDto.forEachIndexed{index, atividade ->
            mapper.map(listaRequisicoes, RequisicoesDoacaoResponseDTO::class.java)
        }
        return listaDto
    }

    fun verficarLista(listaRequisicoes:List<Requisicoes>):Boolean{
        if(!listaRequisicoes.isEmpty()){
            return true
        }
         throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

    fun getDetalhesRequisicao(id:Long):RequisicaoDetalheResponseDTO{
        val requisicao = requisicaoRepository.findById(id.toInt())

        if(requisicao.isEmpty){
            val dto = mapper.map(requisicao, RequisicaoDetalheResponseDTO::class.java)

            return dto
        }
        
        throw ResponseStatusException(HttpStatusCode.valueOf(404))
    }
}