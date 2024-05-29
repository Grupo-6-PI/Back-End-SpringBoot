package school.sptech.projetotfg.controller

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.repository.AtividadeRepository
import school.sptech.projetotfg.service.AtividadeService

@RestController
@RequestMapping("/atividade")
class AtividadeController (
    val atividadeService: AtividadeService
){
    @GetMapping("/lista-atividade")
    fun listarAtividades():ResponseEntity<List<AtividadeResponseDTO>>{
        val atividades = atividadeService.listarAtivadade()
        return ResponseEntity.status(200).body(atividades)
    }

}