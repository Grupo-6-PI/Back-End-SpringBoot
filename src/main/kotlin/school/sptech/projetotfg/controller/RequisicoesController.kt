package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.service.RequisicoesService
import java.time.LocalDate

@RestController
@RequestMapping("/requisicoes")
class RequisicoesController (
    private val requisicoesService: RequisicoesService
){

    @GetMapping("/lista-requisicoes")
    fun listarRequisicoes():ResponseEntity<List<Requisicoes>>{
        val requisicoes = requisicoesService.listarRequisicoes()
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/lista-requisicoes/canceladas")
    fun listarRequisicoesCanceladas():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoes = requisicoesService.listarRequisicoesCanceladas()
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/lista-requisicoes/cumpridas")
    fun listarRequisicoesCumpridas():ResponseEntity<List<RequisicoesDoacaoResponseDTO>>{
        val requisicoes = requisicoesService.listarRequisicoesCumpridas()
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/{id}")
    fun getRequisicao(@PathVariable id:Long):ResponseEntity<Requisicoes>{

        val req = requisicoesService.getRequisicao(id)

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(req)

    }

    @GetMapping("/dash_requisicao")
    fun getRequisicaoDashPorTrimestreTipoESituacao():ResponseEntity<RequisicaoDashDTO>{
        val response = requisicoesService.getRequisicoesTrimestreTipo()
        return ResponseEntity.status(200).body(response)
    }

    @PostMapping
    fun saveRequisicao(@RequestBody requisicao: RequisicaoResquestDTO):ResponseEntity<Requisicoes>{

        var data = LocalDate.now()

        requisicao.data = CalendarioFiltroDTO(data.year,data.monthValue,data.dayOfMonth,data.dayOfWeek.name)

        var requisicaoSalva = requisicoesService.saveRequisicao(requisicao)

        return ResponseEntity.status(201).body(requisicaoSalva)

    }

    @GetMapping("/listar-tipo-requisicao")
    fun listarTipoRequisicao(): ResponseEntity<List<TipoRequisicaoDTO>> {
        val response = requisicoesService.listarTipoRequisicao()
        return ResponseEntity.status(200).body(response)
    }

}