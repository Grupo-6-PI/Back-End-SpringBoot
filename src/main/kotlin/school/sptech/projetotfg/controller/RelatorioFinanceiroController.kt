package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.relatoriofinanceiro.dto.VendaRegistroDTO
import school.sptech.projetotfg.domain.relatoriofinanceiro.dto.VendaResponseDTO
import school.sptech.projetotfg.service.RelatorioFinanceiroService

@RestController
@RequestMapping("/vendas")
class RelatorioFinanceiroController(val service: RelatorioFinanceiroService) {

    @PostMapping
    fun registrarVenda(@RequestBody vendaDto: VendaRegistroDTO): ResponseEntity<String> {
        service.registrarVenda(vendaDto)
        return ResponseEntity.ok("Venda registrada com sucesso!")
    }

    @GetMapping
    fun listarVendas(): ResponseEntity<List<VendaResponseDTO>> =
        ResponseEntity.ok(service.listarVendas())

    @DeleteMapping("/{id}")
    fun apagarVenda(@PathVariable id: Long): ResponseEntity<String> {
        service.apagarVenda(id)
        return ResponseEntity.ok("Venda apagada com sucesso!")
    }
}
