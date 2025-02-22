package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.ReservaAtividade
import school.sptech.projetotfg.domain.atividades.TipoAtividade
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.service.AtividadeService

@RestController
@RequestMapping("/atividade")
class AtividadeController (
    val atividadeService: AtividadeService
){
    @GetMapping("/lista-atividade")
    fun listarAtividades():ResponseEntity<MutableList<ReservaAtividade>>{
        val atividades = atividadeService.listarAtivadade()
        return ResponseEntity.status(200).body(atividades)
    }

    @GetMapping("/{id}")
    fun atividadePorId(@PathVariable id:Long): ResponseEntity<AtividadeResponseDTO> {
        val atividadeEncontrada = atividadeService.atividadePorId(id)
        return ResponseEntity.status(200).body(atividadeEncontrada)
    }

    @GetMapping("/tipos")
    fun listarTipos():ResponseEntity<List<TipoAtividade>>{

        val atividades = atividadeService.listarTipos()
        return ResponseEntity.status(200).body(atividades)

    }

}