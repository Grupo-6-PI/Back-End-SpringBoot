package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.modelmapper.internal.asm.commons.SerialVersionUIDAdder
import org.springframework.stereotype.Service
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.domain.atividades.TipoAtividade
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.dto.ReservaAtividadeResponseDTO
import school.sptech.projetotfg.repository.AtividadeRepository
import school.sptech.projetotfg.repository.ReservaAtividadeRepository
import school.sptech.projetotfg.repository.TipoAtividadeRepository
import java.time.LocalDate

@Service
class AtividadeService(
    private val atividadeRepository: AtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val mapper: ModelMapper,
): school.sptech.projetotfg.complement.Service() {

    fun listarAtivadade(): MutableList<ReservaAtividade>{

            val listaAtividade = atividadeRepository.findAll()

            super.validarLista(listaAtividade)

            val listaResponse = mutableListOf<ReservaAtividade>()

            for (atividade in listaAtividade){
                val reserva = reservaAtividadeRepository.findByAtividadeId(atividade.getId()!!)

                if (reserva.isPresent){
                    listaResponse.add(reserva.get())
                }
                
            }

            return listaResponse

    }

    fun atividadePorId(id:Long):AtividadeResponseDTO{

        val atividade = atividadeRepository.findById(id).get()

        super.validarId(atividade.getId()!!,atividadeRepository)

        val atividadeDto = AtividadeResponseDTO(
            id = atividade.getId(),
            nome = atividade.getNome(),
            tipo = atividade.getTipoAtividade()?.getTipo(),
            comeco = atividade.getHoraComeco(),
            descricao = atividade.getDescricao()
        )

        return atividadeDto

    }

    fun listarTipos():List<TipoAtividade>{

        var lista = tipoAtividadeRepository.findAll()

        super.validarLista(lista)

        return lista

    }

}