package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.dto.CategoriaDTO
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.dto.VendaResponseDTO
import school.sptech.projetotfg.service.RelatorioFinanceiroService

@RestController
@RequestMapping("/vendas")
class RelatorioFinanceiroController(val service: RelatorioFinanceiroService) {

    @PostMapping
    fun registrarVenda(@RequestBody vendaDto: VendaRegistroDTO): ResponseEntity<String> {
        service.registrarVenda(vendaDto)
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Venda Registrada com Sucesso!")
    }

    @GetMapping("/listar-vendas")
    fun listarVendas(): ResponseEntity<List<VendaResponseDTO>> = ResponseEntity.status(HttpStatusCode.valueOf(200)).body(service.listarVendas())

    @DeleteMapping("/{id}")
    fun apagarVenda(@PathVariable id: Long): ResponseEntity<String> {
        service.apagarVenda(id)
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Venda apagada com Sucesso!")
    }

    @GetMapping("/listar-categorias")
    fun listarCategorias(): ResponseEntity<List<Categoria>> =
        ResponseEntity.status(HttpStatusCode.valueOf(200)).body(service.listarCategorias())
}