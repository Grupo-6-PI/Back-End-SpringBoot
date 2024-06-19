package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.dto.ReservaAtividadeResponseDTO
import school.sptech.projetotfg.service.CalendarioService

@RestController
@RequestMapping("/calendarios")
class CalendarioController(private val calendarioService: CalendarioService) {

    @PostMapping
    fun createAtividade(
        @RequestBody atividadeDTO: AtividadeDTO,
        @RequestParam ano: Int,
        @RequestParam mesNumeracao: Int,
        @RequestParam diaNumeracao: Int
    ): ResponseEntity<ReservaAtividade> {
        val filtro = CalendarioFiltroDTO(ano, mesNumeracao, diaNumeracao)
        val reservaAtividade = calendarioService.createAtividade(atividadeDTO, filtro)
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaAtividade)
    }

    @GetMapping
    fun getAllReservasDTO(): ResponseEntity<ReservaAtividadeResponseDTO> {
        val reservas = calendarioService.getAllReserva()
        return ResponseEntity.ok(reservas)
    }

    @GetMapping("/{id}")
    fun getReservaById(@PathVariable id: Long): ResponseEntity<ReservaAtividade> {
        val reserva = calendarioService.getReservaById(id)
        return if (reserva != null) {
            ResponseEntity.ok(reserva)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/atualizacao")
    fun updateReserva(
        @RequestBody atividadeDTO: ReservaAtividade
    ): ResponseEntity<ReservaAtividade> {
        val updatedReserva = calendarioService.updateReserva(atividadeDTO)
        return if (updatedReserva != null) {
            ResponseEntity.ok(updatedReserva)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteReserva(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            calendarioService.deleteReserva(id)
            ResponseEntity.status(200).build()
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }
}
