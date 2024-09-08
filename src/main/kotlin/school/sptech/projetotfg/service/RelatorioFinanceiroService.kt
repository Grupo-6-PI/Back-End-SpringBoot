package school.sptech.projetotfg.service

import org.springframework.stereotype.Service
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.domain.relatoriofinanceiro.dto.VendaRegistroDTO
import school.sptech.projetotfg.domain.relatoriofinanceiro.dto.VendaResponseDTO
import school.sptech.projetotfg.repository.CategoriaRepository
import school.sptech.projetotfg.repository.VendaRepository
import java.time.LocalDateTime

@Service
class RelatorioFinanceiroService(
    val vendaRepository: VendaRepository,
    val categoriaRepository: CategoriaRepository,
    val calendarioService: CalendarioService
) {

    fun registrarVenda(vendaDto: VendaRegistroDTO) {
        val categoria = categoriaRepository.findById(vendaDto.categoriaId)
            .orElseThrow { RuntimeException("Categoria não encontrada") }
        val calendario = calendarioService.getReservaById(vendaDto.calendarioId).getCalendario()
            ?: throw RuntimeException("Calendário não encontrado")
        val venda = Venda(
            id = null,
            quantidade = vendaDto.quantidade,
            valor = vendaDto.valor,
            emailModificador = vendaDto.emailModificador,
            categoria = categoria,
            calendario = calendario
        )
        vendaRepository.save(venda)
    }

    fun listarVendas(): List<VendaResponseDTO> {
        return vendaRepository.findAll().map { venda ->
            VendaResponseDTO(
                quantidade = venda.getQuantidade(),
                valor = venda.getValor(),
                categoria = venda.getCategoria(),
                calendario = venda.getCalendario(),
                dataModificacao = LocalDateTime.now()
            )
        }
    }

    fun apagarVenda(id: Long) {
        vendaRepository.deleteById(id)
    }
}
