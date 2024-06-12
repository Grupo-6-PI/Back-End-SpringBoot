package school.sptech.projetotfg.service

import org.springframework.stereotype.Service
import school.sptech.projetotfg.domain.atividades.*
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CalendarioService(
    private val atividadeRepository: AtividadeRepository,
    private val calendarioRepository: CalendarioRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository
) {

    fun createCalendarioComAtividade(
        ano: Long,
        mesNumeracao: Int,
        diaNumeracao: Int,
        atividadeDTO: AtividadeDTO
    ): ReservaAtividade {
        // Buscar ou criar TipoAtividade
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

        // Criar Calendario
        val calendario = Calendario(
            ano = ano,
            mesNumeracao = mesNumeracao,
            mesNomeacao = LocalDate.of(ano.toInt(), mesNumeracao, diaNumeracao).month.name,
            diaNumeracao = diaNumeracao,
            diaNomeacao = LocalDate.of(ano.toInt(), mesNumeracao, diaNumeracao).dayOfWeek.name
        )
        val savedCalendario = calendarioRepository.save(calendario)

        // Criar ReservaAtividade
        val reservaAtividade = ReservaAtividade(
            calendario = savedCalendario,
            atividade = savedAtividade,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )
        return reservaAtividadeRepository.save(reservaAtividade)
    }

    fun getAllReservas(): List<ReservaAtividade> = reservaAtividadeRepository.findAll()

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

    fun deleteReserva(id: Long): Boolean {
        return if (reservaAtividadeRepository.existsById(id)) {
            reservaAtividadeRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
