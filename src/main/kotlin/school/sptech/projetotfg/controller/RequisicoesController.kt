package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicaoDashDTO
import school.sptech.projetotfg.dto.RequisicaoResquestDTO
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.service.RequisicoesService

@RestController
@RequestMapping("/requisicoes")
class RequisicoesController (
    private val requisicoesService: RequisicoesService
){

    @GetMapping("/lista-requisicoes")
    fun listarRequisicoes():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoes = requisicoesService.listarRequisicoes()
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/dash_requisicao")
    fun getRequisicaoDashPorTrimestreTipoESituacao():ResponseEntity<RequisicaoDashDTO>{
        val response = requisicoesService.getRequisicoesTrimestreTipo()
        return ResponseEntity.status(200).body(response)
    }

    @PostMapping
    fun saveRequisicao(@RequestBody requisicao: RequisicaoResquestDTO):ResponseEntity<Requisicoes>{

        var requisicaoSalva = requisicoesService.saveRequisicao(requisicao)

        return ResponseEntity.status(201).body(requisicaoSalva)

    }

}