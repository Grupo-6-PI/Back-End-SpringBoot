package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.service.RequisicoesService
import java.time.LocalDate
import java.util.Stack
import java.util.concurrent.ArrayBlockingQueue

@RestController
@RequestMapping("/requisicoes")
class RequisicoesController (
    private val requisicoesService: RequisicoesService
){

    @GetMapping("/lista-requisicoes/{id}")
    fun listarRequisicoes(@PathVariable id:Long ):ResponseEntity<ArrayBlockingQueue<RequisicoesDTO>>{
        val requisicoes = requisicoesService.listarRequisicoes(id)
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/lista-requisicoes/canceladas")
    fun listarRequisicoesCanceladas():ResponseEntity<ArrayBlockingQueue<RequisicaoResponseDTO>>{
        val requisicoes = requisicoesService.listarRequisicoesCanceladas()
        return ResponseEntity.status(200).body(requisicoes)
    }

    @GetMapping("/lista-requisicoes/cumpridas")
    fun listarRequisicoesCumpridas():ResponseEntity<ArrayBlockingQueue<RequisicaoResponseDTO>>{
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

    @GetMapping("/dash_requisicao/semanal")
    fun getRequisicaoDashSemana():ResponseEntity<RequisicoesGraficoSemanalDTO>{
        val response = requisicoesService.getRequisicoesSemanal()
        return ResponseEntity.status(200).body(response)
    }

    @GetMapping("/dash_requisicao/diario")
    fun getRequisicaoDashDiario():ResponseEntity<List<RequisicoesGraficoDiarioDTO>>{
        val response = requisicoesService.getRequisicoesDiario()
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

    @PutMapping("/{id}/aceitar")
    fun aceitarRequisicao(@PathVariable id: Long): ResponseEntity<Requisicoes> {
        val response = requisicoesService.alterarSituacao(id, 1)
        return ResponseEntity.status(200).body(response)
    }

    @PutMapping("/{id}/resetar")
    fun resetarRequisicao(@PathVariable id: Long): ResponseEntity<Requisicoes> {
        val response = requisicoesService.alterarSituacao(id, 3)
        return ResponseEntity.status(200).body(response)
    }

    @PutMapping("/{id}/recusar")
    fun recusarRequisicao(@PathVariable id: Long): ResponseEntity<Requisicoes> {
        val response = requisicoesService.alterarSituacao(id, 2)
        return ResponseEntity.status(200).body(response)
    }

    @GetMapping("/listar-pedidos-adm")
    fun listarPedidosADM():ResponseEntity<ArrayBlockingQueue<RequisicaoResponseDTO>>{

        val response = requisicoesService.listRequisicaoADM()
        return ResponseEntity.status(200).body(response)

    }

    @GetMapping("/negadas")
    fun getRequisicoesNegadasUltimos30Dias(): ResponseEntity<Long> {
        val totalNegadas = requisicoesService.getQuantidadeRequisicoesNegadasUltimos30Dias()
        return ResponseEntity.ok(totalNegadas)
    }

    @GetMapping("/totais")
    fun getTotalRequisicoesUltimos30Dias(): ResponseEntity<Long> {
        val totalRequisicoes = requisicoesService.getQuantidadeTotalRequisicoesUltimos30Dias()
        return ResponseEntity.ok(totalRequisicoes)
    }

}