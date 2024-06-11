package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.service.CalendarioService

@RestController
@RequestMapping("/calendarios")
class CalendarioController(private val calendarioService: CalendarioService) {

    @PostMapping
    fun createCalendarioComAtividade(
        @RequestParam ano: Long,
        @RequestParam mesNumeracao: Int,
        @RequestParam diaNumeracao: Int,
        @RequestBody atividadeDTO: AtividadeDTO
    ): ResponseEntity<ReservaAtividade> {
        val reservaAtividade = calendarioService.createCalendarioComAtividade(
            ano, mesNumeracao, diaNumeracao, atividadeDTO
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaAtividade)
    }

    @GetMapping
    fun getAllReservas(): List<ReservaAtividade> = calendarioService.getAllReservas()

    @GetMapping("/{id}")
    fun getReservaById(@PathVariable id: Long): ResponseEntity<ReservaAtividade> {
        val reserva = calendarioService.getReservaById(id)
        return if (reserva != null) {
            ResponseEntity.ok(reserva)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateReserva(
        @PathVariable id: Long,
        @RequestBody atividadeDTO: AtividadeDTO
    ): ResponseEntity<ReservaAtividade> {
        val updatedReserva = calendarioService.updateReserva(id, atividadeDTO)
        return if (updatedReserva != null) {
            ResponseEntity.ok(updatedReserva)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteReserva(@PathVariable id: Long): ResponseEntity<Void> {
        return if (calendarioService.deleteReserva(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
