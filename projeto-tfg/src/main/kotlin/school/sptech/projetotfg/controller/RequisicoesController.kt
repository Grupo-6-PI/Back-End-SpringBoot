package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.service.RequisicoesService

@RestController
@RequestMapping("/requisicoes")
class RequisicoesController (
    val requisicoesService: RequisicoesService
){

    @GetMapping("/lista-requisicoes")
    fun listarRequisicoes():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoes = requisicoesService.listarRequisicoes()
        return ResponseEntity.status(200).body(requisicoes)
    }
}