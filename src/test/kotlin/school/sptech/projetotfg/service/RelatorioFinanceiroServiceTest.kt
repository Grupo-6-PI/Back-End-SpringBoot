/*
package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.modelmapper.ModelMapper
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.relatoriofinanceiro.Categoria
import school.sptech.projetotfg.domain.relatoriofinanceiro.Venda
import school.sptech.projetotfg.dto.VendaRegistroDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalTime
import java.util.*

class RelatorioFinanceiroServiceTest {
    private lateinit var calendarioRepository: CalendarioRepository
    private lateinit var categoriaRepository: CategoriaRepository
    private lateinit var vendaRepository: VendaRepository
    private lateinit var relatorioFinanceiroService: RelatorioFinanceiroService
    private lateinit var mapper: ModelMapper

    @BeforeEach
    fun instanciar() {
        calendarioRepository = Mockito.mock(CalendarioRepository::class.java)
        categoriaRepository = Mockito.mock(CategoriaRepository::class.java)
        vendaRepository = Mockito.mock(VendaRepository::class.java)
        mapper = Mockito.mock(ModelMapper::class.java)
        relatorioFinanceiroService = RelatorioFinanceiroService(vendaRepository,categoriaRepository, calendarioRepository, mapper)
    }

    @Test
    fun `deve registrar uma venda`() {
        val categoria = Categoria(1, "camiseta")
        `when`(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria))

        val calendario = Calendario(1, 2024, 1, "Janeiro", 1, "Domingo")
        `when`(calendarioRepository.findById(1)).thenReturn(Optional.of(calendario))

        val venda = VendaRegistroDTO(1, 2, 20.0, categoria, "teste@teste.com", calendario)

        relatorioFinanceiroService.registrarVenda(venda)

        Mockito.verify(vendaRepository).save(Mockito.any(Venda::class.java))
    }


    @Test
    fun `deve retonar a lista esperada`() {
        val listaEsperada = vendaRepository.findAll()

        `when`(vendaRepository.findAll()).thenReturn(listaEsperada)

        val resultado = relatorioFinanceiroService.listarVendas()

        assertEquals(listaEsperada.size,resultado.size)

        assertEquals(listaEsperada,resultado)
    }

    @Test
    fun `deve deletar uma venda com sucesso`() {
        `when`(vendaRepository.existsById(1)).thenReturn(true)

        relatorioFinanceiroService.apagarVenda(1)

        Mockito.verify(vendaRepository, Mockito.times(1)).deleteById(1)
    }
}
*/