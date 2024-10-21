package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.CategoriaDTO
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.dto.VendaResponseDTO
import school.sptech.projetotfg.repository.CalendarioRepository
import school.sptech.projetotfg.repository.CategoriaRepository
import school.sptech.projetotfg.repository.VendaRepository
import java.time.LocalDateTime

@Service
class RelatorioFinanceiroService(
    val vendaRepository: VendaRepository,
    val categoriaRepository: CategoriaRepository,
    val calendarioRepository: CalendarioRepository,
    val mapper: ModelMapper
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
                id = venda.getId(),
                quantidade = venda.getQuantidade(),
                valor = venda.getValor(),
                categoria = CategoriaDTO(
                    id = venda.getCategoria()!!.getId(),
                    nome = venda.getCategoria()!!.getNome()
                ),
                calendario = venda.getCalendario(),
                dataModificacao = LocalDateTime.now()
            )
        }
    }

    fun apagarVenda(id: Long) {
        vendaRepository.deleteById(id)
    }

    fun listarCategorias(): List<Categoria> {
        return categoriaRepository.findAll().map {
            categoria -> Categoria(
                id=categoria.getId(),
                nome = categoria.getNome()
            )
        }
    }

}
