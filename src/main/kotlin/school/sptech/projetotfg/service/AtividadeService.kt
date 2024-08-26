package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
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
): school.sptech.projetotfg.complement.Service() {

    fun listarAtivadade():List<Atividade>{

            val listaAtividade = atividadeRepository.findAll()

            super.validarLista(listaAtividade)

            return listaAtividade

    }

    fun atividadePorId(id:Int):AtividadeResponseDTO{

        val atividade = atividadeRepository.findById(id.toLong()).get()

        super.validarId(atividade.getId(),atividadeRepository)

        val atividadeDto:AtividadeResponseDTO = mapper.map(atividade, AtividadeResponseDTO::class.java)

        return atividadeDto

    }

    fun listarTipos():List<TipoAtividade>{

        var lista = tipoAtividadeRepository.findAll()

        super.validarLista(lista)

        return lista

    }

}