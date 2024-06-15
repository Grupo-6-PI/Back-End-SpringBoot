package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.service.CalendarioService

@RestController
@RequestMapping("/calendario-atividades")
class CalendarioController(private val calendarioService: CalendarioService) {

    @PostMapping
    fun createAtividade(
        @RequestBody atividadeDTO: AtividadeDTO
    ): ResponseEntity<ReservaAtividade> {
        val reservaAtividade = calendarioService.createAtividade(
            atividadeDTO
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaAtividade)
    }

    @GetMapping
    fun getAllReservas(): ResponseEntity<Map<String, List<ReservaAtividade>>> {
        val atividades = calendarioService.getAllReservas()
        return ResponseEntity.ok(atividades)
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
    fun excluirUsuario(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            var atividadeDTO = calendarioService.getReservaById(id)
            calendarioService.deleteReserva(id)
            return ResponseEntity.status(200).body(atividadeDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).body("Usuário não encontrado")
        }
    }
}
