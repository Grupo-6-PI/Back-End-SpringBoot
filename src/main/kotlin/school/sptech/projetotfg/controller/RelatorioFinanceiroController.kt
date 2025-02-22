package school.sptech.projetotfg.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.VendaRepository
import school.sptech.projetotfg.service.RelatorioFinanceiroService
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate

@RestController
@RequestMapping("/vendas")
class RelatorioFinanceiroController(val service: RelatorioFinanceiroService) {

    @PostMapping("/registrarVenda")
    fun registrarVenda(@RequestBody vendaDto: VendaRegistroDTO): ResponseEntity<String> {

        val filtroDTO = CalendarioFiltroDTO(
            LocalDate.now().year,
            LocalDate.now().monthValue,
            LocalDate.now().dayOfMonth,
            null
        )

        vendaDto.calendario = filtroDTO

        service.registrarVenda(vendaDto)
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Venda Registrada com Sucesso!")
    }

    @GetMapping("/listar-vendas")
    fun listarVendas(@RequestParam email: String): ResponseEntity<List<VendaResponseDTO>> {
        val vendas = service.listarVendas(email)
        return ResponseEntity.ok(vendas)
    }
    @DeleteMapping("/{id}")
    fun apagarVenda(@PathVariable id: Long): ResponseEntity<String> {
        service.apagarVenda(id)
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Venda apagada com Sucesso!")
    }

    @GetMapping("/listar-categorias")
    fun listarCategorias(): ResponseEntity<List<Categoria>> =
        ResponseEntity.status(HttpStatusCode.valueOf(200)).body(service.listarCategorias())

    @GetMapping("/exportar")
    fun exportarVendasCsv(
        @RequestParam dataInicio: String,
        @RequestParam dataFim: String
    ): ResponseEntity<ByteArray> {
        val dataInicioParsed = LocalDate.parse(dataInicio)
        val dataFimParsed = LocalDate.parse(dataFim)

        val nomeArquivo = service.gerarCsvVendasPorData(dataInicioParsed, dataFimParsed)
        val file = Path.of(nomeArquivo).toFile()

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$nomeArquivo\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(Files.readAllBytes(file.toPath()))
    }

    @GetMapping("/kpi-ultimos-30-dias")
    fun calcularKpiUltimos30Dias(): ResponseEntity<KpiVendaResponseDTO> {
        val kpi = service.calcularKpiUltimos30Dias()
        return ResponseEntity.ok(kpi)
    }

}
