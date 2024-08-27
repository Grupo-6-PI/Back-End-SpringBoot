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
import kotlin.contracts.contract

@Service
class CalendarioService(
    private val atividadeRepository: AtividadeRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository,
    private val calendarioRepository: CalendarioRepository // Novo repositório para Calendario
):school.sptech.projetotfg.complement.Service() {

    fun createAtividade(
        atividadeDTO: AtividadeDTO
    ): ReservaAtividade {

        val tipoAtividade = tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)
            .orElseThrow { IllegalArgumentException("Tipo de Atividade não encontrado") }

        val semana:Array<Calendario?> = getDomingo(atividadeDTO.filtrodto)

        var dia:Calendario = getDia(atividadeDTO.filtrodto.diaNomeacao,semana!!)!!

        // Criar Atividade
        val atividade = Atividade(
            id = null,
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
            id = null,
            atividade = savedAtividade,
            calendario = dia,
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
            val calendario = it.getCalendario()

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

    fun getReservaById(id: Long): ReservaAtividade?{
        var resposta = reservaAtividadeRepository.findByAtividadeId(id)
        return resposta
    }

    fun updateReserva(
        atividadeDTO: ReservaAtividade
    ): ReservaAtividade? {

        var reserva = reservaAtividadeRepository.findById(atividadeDTO.getId()).get()

        reserva.getAtividade().setNome(atividadeDTO.getAtividade().getNome())
        reserva.getAtividade().setTipoAtividade(atividadeDTO.getAtividade().getTipoAtividade())

        reserva.getAtividade().setDescricao(atividadeDTO.getAtividade().getDescricao())

        reserva.getCalendario().setAno(atividadeDTO.getCalendario().getAno())
        reserva.getCalendario().setMesNumeracao(atividadeDTO.getCalendario().getMesNumeracao())
        reserva.getCalendario().setDiaNumeracao(atividadeDTO.getCalendario().getDiaNumeracao())

        reserva.getAtividade().setHoraComeco(atividadeDTO.getAtividade().getHoraComeco())
        reserva.getAtividade().setHoraFinal(atividadeDTO.getAtividade().getHoraFinal())

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

    fun getDomingo(calendarioFiltroDTO: CalendarioFiltroDTO):Array<Calendario?>{

        var idDiaAtual:Long = calendarioRepository.getSemana(calendarioFiltroDTO.diaNumeracao,calendarioFiltroDTO.mesNumeracao,calendarioFiltroDTO.ano).get()

        var idDomingo:Long = 0

        var contador = 7

        while (contador >= 1) {

            var dia = calendarioRepository.findById(idDiaAtual-contador).get()

            if (dia.getDiaNomeacao().equals("Domingo")){

                idDomingo = dia.getId()

                break

            }

            contador--
        }

        var sem = getSemana(idDomingo)

        return sem

    }

    fun getSemana(idDomingo:Long):Array<Calendario?>{

        var semana: Array<Calendario?> = arrayOfNulls<Calendario>(7)

        var contador:Int = 0

        while (contador < 7) {

            semana.set(contador,calendarioRepository.findById(idDomingo+contador).get())

            contador++
        }

        return semana

    }

    fun getDia(dia:String,semana:Array<Calendario?>):Calendario?{

        for (diaSemana in semana){

            if (diaSemana!!.getDiaNomeacao().equals(dia)){

                return diaSemana

            }

        }

        return null

    }

}
