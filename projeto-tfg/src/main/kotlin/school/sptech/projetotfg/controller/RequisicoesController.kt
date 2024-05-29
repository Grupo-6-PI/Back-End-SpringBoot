package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetotfg.dto.RequisicaoDetalheResponseDTO
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

    @GetMapping("/lista-requisicoes-aceitas")
    fun listarRequisicoesAceitas():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoesAceitas = requisicoesService.listarRequisicoesAceitas()
        return ResponseEntity.status(200).body(requisicoesAceitas)
    }

    @GetMapping("/lista-requisicoes-negadas")
    fun listarRequisicoesNegadas():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoesNegadas = requisicoesService.listarRequisicoesNegadas()
        return ResponseEntity.status(200).body(requisicoesNegadas)
    }

    @GetMapping("/lista-requisicoes-abertas")
    fun listarRequisicoesAbertas():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoesAbertas = requisicoesService.listarRequisicoesAbertas()
        return ResponseEntity.status(200).body(requisicoesAbertas)
    }

    @GetMapping("/detalhado")
    fun getRequisicaoDetalhePorId(@RequestParam id:Long):RequisicaoDetalheResponseDTO{
        val requisicaoDetalhada = requisicoesService.getDetalhesRequisicao(id)
        return requisicaoDetalhada
    }
}