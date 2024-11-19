package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.CategoriaDTO
import school.sptech.projetotfg.dto.KpiResponseDTO
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.dto.VendaResponseDTO
import school.sptech.projetotfg.repository.CalendarioRepository
import school.sptech.projetotfg.repository.CategoriaRepository
import school.sptech.projetotfg.repository.VendaRepository
import java.io.FileWriter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.jvm.optionals.toList

@Service
class RelatorioFinanceiroService(
    val vendaRepository: VendaRepository,
    val categoriaRepository: CategoriaRepository,
    val calendarioRepository: CalendarioRepository,
    val mapper: ModelMapper
) {

    fun registrarVenda(vendaDto: VendaRegistroDTO) {

        var calendario = calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(vendaDto.calendario!!.ano,vendaDto.calendario!!.mesNumeracao,vendaDto.calendario!!.diaNumeracao).get()

        val venda = Venda(
            id = null,
            quantidade = vendaDto.quantidade,
            valor = vendaDto.valor,
            emailModificador = vendaDto.emailModificador,
            categoria = vendaDto.categoria,
            calendario = calendario
        )
        vendaRepository.save(venda)
    }

    fun listarVendas(emailUsuario: String): List<VendaResponseDTO> {
        val hoje = LocalDate.now()

        return vendaRepository.findAll()
            .filter { venda ->
                venda.getEmailModificador() == emailUsuario &&
                        venda.getCalendario()?.getAno() == hoje.year &&
                        venda.getCalendario()?.getMesNumeracao() == hoje.monthValue &&
                        venda.getCalendario()?.getDiaNumeracao() == hoje.dayOfMonth
            }
            .map { venda ->
                val categoriaDTO = CategoriaDTO(
                    id = venda.getCategoria()?.getId(),
                    nome = venda.getCategoria()?.getNome()
                )
                VendaResponseDTO(
                    id = venda.getId(),
                    quantidade = venda.getQuantidade(),
                    valor = venda.getValor(),
                    categoria = categoriaDTO,
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

    fun calcularKpiBazar():KpiResponseDTO{

        var listaVendas:List<Venda> = vendaRepository.getAllD30()

        if(listaVendas.isNotEmpty()){

            var valorTotal = 0.0

            listaVendas.map {
                valorTotal += it.getValor()!!
            }

            var kpi = KpiResponseDTO(valorTotal)

            return kpi
        }else{
            throw ResponseStatusException(HttpStatus.NO_CONTENT, "Não foram encontradas Vendas nos últimos 30 dias")
        }

    }

    fun gerarCsvVendasPorData(dataInicio: LocalDate, dataFim: LocalDate): String {
        val ano = dataInicio.year
        val mesInicio = dataInicio.monthValue
        val mesFim = dataFim.monthValue
        val diaInicio = dataInicio.dayOfMonth
        val diaFim = dataFim.dayOfMonth

        // Busca as vendas de acordo com o intervalo de data
        val vendas = vendaRepository.findVendasByDataInterval(ano, mesInicio, mesFim, diaInicio, diaFim)

        // Nome do arquivo com data e hora no formato "relatorio_vendas_yyyyMMdd_HHmmss.csv"
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val nomeArquivo = "relatorio_vendas${timestamp}.csv"

        FileWriter(nomeArquivo).use { writer ->
            Formatter(writer).use { saida ->
                saida.format("ID;Data;Categoria;Quantidade;Valor\n")
                vendas.forEach { venda ->
                    saida.format(
                        "%d;%02d/%02d/%04d;%s;%d;%,2f\n",
                        venda.getId(),
                        venda.getCalendario()?.getDiaNumeracao() ?: 0,
                        venda.getCalendario()?.getMesNumeracao() ?: 0,
                        venda.getCalendario()?.getAno() ?: 0,
                        venda.getCategoria()?.getNome() ?: "Sem Categoria",
                        venda.getQuantidade(),
                        venda.getValor()
                    )
                }
            }
        }

        return nomeArquivo
    }
}


