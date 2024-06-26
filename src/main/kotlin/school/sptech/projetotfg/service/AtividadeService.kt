package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.TipoAtividade
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.repository.AtividadeRepository
import school.sptech.projetotfg.repository.TipoAtividadeRepository

@Service
class AtividadeService(
    private val atividadeRepository: AtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository,
    private val mapper: ModelMapper,
) {
    fun listarAtivadade():List<Atividade>{

            val listaAtividade = atividadeRepository.findAll()
            if(!listaAtividade.isEmpty()){
                return listaAtividade
            }
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

    fun atividadePorId(id:Int):AtividadeResponseDTO{
        val atividade = atividadeRepository.findById(id.toLong()).get()
        if(!atividade.equals(null)){
            val atividadeDto:AtividadeResponseDTO = mapper.map(atividade, AtividadeResponseDTO::class.java)
            return atividadeDto
        }
        throw ResponseStatusException(HttpStatusCode.valueOf(404))
    }

    fun listarTipos():List<TipoAtividade>{
        var lista = tipoAtividadeRepository.findAll()
        if(lista.isNotEmpty()){
            return lista
        }else{
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }
    }

}