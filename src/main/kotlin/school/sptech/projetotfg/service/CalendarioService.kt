package school.sptech.projetotfg.service

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.*
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.dto.ReservaAtividadeResponseDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.util.Objects

@Service
class CalendarioService(
    private val atividadeRepository: AtividadeRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository,
    private val calendarioRepository: CalendarioRepository // Novo repositório para Calendario
) {

    fun createAtividade(
        atividadeDTO: AtividadeDTO,
        calendarioFiltroDTO: CalendarioFiltroDTO
    ): ReservaAtividade {

        val tipoAtividade = tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)
            .orElseThrow { IllegalArgumentException("Tipo de Atividade não encontrado") }

        // Buscar o calendário existente
        val calendario = calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(
            calendarioFiltroDTO.ano, calendarioFiltroDTO.mesNumeracao, calendarioFiltroDTO.diaNumeracao
        ).orElseThrow { IllegalArgumentException("Calendário não encontrado para a data fornecida") }

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

        // Criar ReservaAtividade com o calendário encontrado
        val reservaAtividade = ReservaAtividade(
            atividade = savedAtividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )
        return reservaAtividadeRepository.save(reservaAtividade)
    }

    fun getAllReserva():ReservaAtividadeResponseDTO{

        var dto = ReservaAtividadeResponseDTO()

        val reservaAtividade = reservaAtividadeRepository.findAll()

        reservaAtividade.map {
            val calendario = it.calendario

            when(calendario!!.getDiaNomeacao()){
                "Domingo" -> {
                    dto.domingo.add(it)
                }
                "Segunda-Feira" -> {
                    dto.segunda.add(it)
                }
                "Terça-Feira" -> {
                    dto.terca.add(it)
                }
                "Quarta-Feira" -> {
                    dto.quarta.add(it)
                }
                "Quinta-Feira" -> {
                    dto.quinta.add(it)
                }
                "Sexta-Feira" -> {
                    dto.sexta.add(it)
                }
                "Sábado" -> {
                    dto.sabado.add(it)
                }
                else -> {
                    throw IllegalArgumentException("O Dia da Semana não existe")
                }
            }

        }

        return dto

    }

//    fun getAllReservas(calendarioFiltroDTO: CalendarioFiltroDTO): Map<String, List<ReservaAtividade>> {
//        val calendario = calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(
//            calendarioFiltroDTO.ano, calendarioFiltroDTO.mesNumeracao, calendarioFiltroDTO.diaNumeracao
//        )
//
//        if(Objects.isNull(calendario)) {
//            throw IllegalArgumentException("Nenhum calendário encontrado para a data fornecida")
//        }
//
//        val reservas:List<ReservaAtividade> = reservaAtividadeRepository.findAll()
//
//
//
//        return reservas.groupBy {
//            val data = it.getCalendario()
//            "${data.getAno()}-${data.getMesNumeracao()}-${data.getDiaNumeracao()}"
//        }
//    }

    fun getReservaById(id: Long): ReservaAtividade?{
        var resposta = reservaAtividadeRepository.findByAtividadeId(id)
        return resposta
    }

    fun updateReserva(
        atividadeDTO: ReservaAtividade
    ): ReservaAtividade? {

        var reserva = reservaAtividadeRepository.findById(atividadeDTO.id!!).get()

        reserva.atividade!!.nome = atividadeDTO.atividade!!.nome
        reserva.atividade!!.tipoAtividade!!.setId(atividadeDTO.atividade!!.tipoAtividade!!.getId())

        reserva.atividade!!.descricao = atividadeDTO.atividade!!.descricao

        reserva.calendario!!.setAno(atividadeDTO.calendario!!.getAno())
        reserva.calendario!!.setMesNumeracao(atividadeDTO.calendario!!.getMesNumeracao())
        reserva.calendario!!.setDiaNumeracao(atividadeDTO.calendario!!.getDiaNumeracao())

        reserva.atividade!!.horaComeco = atividadeDTO.atividade!!.horaComeco
        reserva.atividade!!.horaFinal = atividadeDTO.atividade!!.horaFinal

        return reservaAtividadeRepository.save(reserva)
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
