package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.dto.VendaResponseDTO
import school.sptech.projetotfg.repository.CategoriaRepository
import school.sptech.projetotfg.repository.VendaRepository
import java.time.LocalDateTime

@Service
class RelatorioFinanceiroService(
    val vendaRepository: VendaRepository,
    val categoriaRepository: CategoriaRepository,
    val calendarioService: ModelMapper
) {

    fun registrarVenda(vendaDto: VendaRegistroDTO) {
        val venda = Venda(
            id = null,
            quantidade = vendaDto.quantidade,
            valor = vendaDto.valor,
            emailModificador = vendaDto.emailModificador,
            categoria = vendaDto.categoria,
            calendario = vendaDto.calendario

        )
        vendaRepository.save(venda)
    }

    fun listarVendas(): List<VendaResponseDTO> {
        return vendaRepository.findAll().map { venda ->
            VendaResponseDTO(
                id = null,
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
