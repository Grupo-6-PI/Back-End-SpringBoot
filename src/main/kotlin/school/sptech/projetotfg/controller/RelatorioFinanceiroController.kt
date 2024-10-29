package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.dto.CategoriaDTO
import school.sptech.projetotfg.dto.KpiVendaResponseDTO
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.dto.VendaResponseDTO
import school.sptech.projetotfg.repository.VendaRepository
import school.sptech.projetotfg.service.RelatorioFinanceiroService
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

    @GetMapping("/kpi/receita")
    fun receitaBazar(): ResponseEntity<KpiVendaResponseDTO>{
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(service.calcularReceitaBazar())
    }
}