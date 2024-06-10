package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.repository.AtividadeRepository

@Service
class AtividadeService(
    private val atividadeRepository: AtividadeRepository,
    private val mapper: ModelMapper,
) {
    fun listarAtivadade():List<AtividadeResponseDTO>{

            val listaAtividade = atividadeRepository.findAll()
            if(!listaAtividade.isEmpty()){
                val listaDto:MutableList<AtividadeResponseDTO> = mutableListOf()
                listaDto.forEachIndexed{index, atividade ->
                    mapper.map(listaAtividade,AtividadeResponseDTO::class.java)
                }
                return listaDto
            }
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

    fun atividadePorId(id:Int):AtividadeResponseDTO{
        val atividade = atividadeRepository.findById(id).get()
        if(!atividade.equals(null)){
            val atividadeDto:AtividadeResponseDTO = mapper.map(atividade, AtividadeResponseDTO::class.java)
            return atividadeDto
        }
        throw ResponseStatusException(HttpStatusCode.valueOf(404))
    }

}