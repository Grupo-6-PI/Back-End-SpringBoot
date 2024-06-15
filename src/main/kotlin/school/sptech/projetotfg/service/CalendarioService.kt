package school.sptech.projetotfg.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

@Service
class CalendarioService(
    private val atividadeRepository: AtividadeRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository
) {

    fun createAtividade(
        atividadeDTO: AtividadeDTO
    ): ReservaAtividade {

        val tipoAtividade = tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)
            .orElseThrow { IllegalArgumentException("Tipo de Atividade não encontrado") }

        // Criar Atividade
        val atividade = Atividade(
            nome = atividadeDTO.nome,
            horaComeco = atividadeDTO.horaComeco,
            horaFinal = atividadeDTO.horaFinal,
            descricao = atividadeDTO.descricao,
            tipoAtividade = tipoAtividade,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )
        val savedAtividade = atividadeRepository.save(atividade)

        // Criar ReservaAtividade
        val reservaAtividade = ReservaAtividade(
            atividade = savedAtividade,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )
        return reservaAtividadeRepository.save(reservaAtividade)
    }

    fun getAllReservas(): Map<String, List<ReservaAtividade>> {
        val reservas = reservaAtividadeRepository.findAll()
        return reservas.groupBy {
            val data = it.getdataCriacao().toLocalDate()
            "${data.year}-${data.month.value}-${data.dayOfMonth}"
        }
    }

    fun getReservaById(id: Long): ReservaAtividade? = reservaAtividadeRepository.findById(id).orElse(null)

    fun updateReserva(
        id: Long,
        atividadeDTO: AtividadeDTO
    ): ReservaAtividade? {
        val reservaAtividade = reservaAtividadeRepository.findById(id).orElse(null) ?: return null

        // Atualizar Atividade
        val atividade = reservaAtividade.atividade.apply {
            nome = atividadeDTO.nome
            horaComeco = atividadeDTO.horaComeco
            horaFinal = atividadeDTO.horaFinal
            descricao = atividadeDTO.descricao
            tipoAtividade = tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)
                .orElseThrow { IllegalArgumentException("Tipo de Atividade não encontrado") }
            emailModificador = atividadeDTO.emailModificador
            dataUltimaAtualizacao = LocalDateTime.now()
        }
        atividadeRepository.save(atividade)

        // Atualizar ReservaAtividade
        reservaAtividade.apply {
            this.atividade = atividade
            this.emailModificador = atividadeDTO.emailModificador
            this.dataUltimaAtualizacao = LocalDateTime.now()
        }
        return reservaAtividadeRepository.save(reservaAtividade)
    }

    fun deleteReserva(id: Long) {
        if (!reservaAtividadeRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada")
        }
        try {
            reservaAtividadeRepository.deleteById(id)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir Atividade: ${ex.message}")
        }
    }
}
