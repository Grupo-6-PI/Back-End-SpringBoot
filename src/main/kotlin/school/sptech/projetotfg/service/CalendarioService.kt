package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.*
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.cadastro.Endereco
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.dto.ReservaAtividadeResponseDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.util.Optional

@Service
class CalendarioService(
    private val atividadeRepository: AtividadeRepository,
    private val reservaAtividadeRepository: ReservaAtividadeRepository,
    private val tipoAtividadeRepository: TipoAtividadeRepository,
    private val calendarioRepository: CalendarioRepository,
    private val cidadeRepository: CidadeRepository,
    private val bairroRepository:BairroRepository,
    private val situacaoRepository: SituacaoRepository,
    private val enderecoRepository: EnderecoRepository,
    private val modelMapper: ModelMapper

):school.sptech.projetotfg.complement.Service() {

    fun createAtividade(
        atividadeDTO: AtividadeDTO
    ): ReservaAtividade {

        val tipoAtividade = tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)
            .orElseThrow { IllegalArgumentException("Tipo de Atividade não encontrado") }
        
        var calendarioId = calendarioRepository.getSemana(atividadeDTO.filtrodto.diaNumeracao,atividadeDTO.filtrodto.mesNumeracao,atividadeDTO.filtrodto.ano).get()

        var cidade = cidadeRepository.findByNome(atividadeDTO.endereco.cidade).get()

        val bairro = bairroRepository.findByNomeAndCidadeId(atividadeDTO.endereco.bairro, cidade.getId()!!).get()

        var endereco = Endereco(
            id = null,
            logradouro = atividadeDTO.endereco.logradouro,
            numero = atividadeDTO.endereco.numero,
            cep = null,
            bairro = bairro,
            situacao = situacaoRepository.findById(1).get()
        )

        val endeFinal = enderecoRepository.save(endereco)

        var calendario = calendarioRepository.findById(calendarioId).get()

        atividadeDTO.filtrodto.diaNomeacao = calendario.getDiaNomeacao()

        val semana:Array<Calendario?> = getDomingo(atividadeDTO.filtrodto)

        var dia:Calendario? = getDia(atividadeDTO.filtrodto.diaNomeacao!!,semana)

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
            emailModificador = atividadeDTO.emailModificador,
            endereco = endeFinal
        )
        return reservaAtividadeRepository.save(reservaAtividade)
    }

    fun getAllReserva(calendarioFiltro:CalendarioFiltroDTO):ReservaAtividadeResponseDTO{

        val dto = ReservaAtividadeResponseDTO()

        val semana = getDomingo(calendarioFiltro)

        val reservaAtividade:MutableList<ReservaAtividade> = mutableListOf()

        for (dia in semana){

            reservaAtividadeRepository.findAllByCalendarioId(dia!!.getId()!!).forEach {
                reservaAtividade.add(it)
            }

        }

        reservaAtividade.map {
            val calendario = it.getCalendario()

            when(calendario?.getDiaNomeacao()){
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

    fun getReservaById(id: Long): ReservaAtividade{

        super.validarId(id,reservaAtividadeRepository)

        var resposta = reservaAtividadeRepository.findById(id).get()

        return resposta

    }

    fun updateReserva(
        atividadeDTO: ReservaAtividade
    ): ReservaAtividade? {

        super.validarId(atividadeDTO.getId()!!,reservaAtividadeRepository)
        var reserva = reservaAtividadeRepository.findById(atividadeDTO.getId()!!).get()

        var new_calendario_id = calendarioRepository.getSemana(atividadeDTO.getCalendario()!!.getDiaNumeracao()!!,atividadeDTO.getCalendario()!!.getMesNumeracao()!!,atividadeDTO.getCalendario()!!.getAno()!!).get()

        var new_calendario = calendarioRepository.findById(new_calendario_id)

        atividadeDTO.setCalendario(new_calendario.get())

        reserva = atividadeDTO

        val newAtt = reservaAtividadeRepository.save(reserva)

        return newAtt

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

                idDomingo = dia.getId()!!

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

            if (diaSemana?.getDiaNomeacao().equals(dia)){

                return diaSemana

            }

        }

        return null

    }

}
