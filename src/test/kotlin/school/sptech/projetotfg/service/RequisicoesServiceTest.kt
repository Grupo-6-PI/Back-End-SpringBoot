package school.sptech.projetotfg.service

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.modelmapper.ModelMapper
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicaoDashDTO
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository
import java.util.*

class RequisicoesServiceTest{

    lateinit var requisicaoRepository: RequisicaoRepository
    lateinit var requisicaoService: RequisicoesService
    lateinit var mapper: ModelMapper

    @BeforeEach
    fun instanciar(){
        requisicaoRepository = mock(RequisicaoRepository::class.java)
        requisicaoService = mock(RequisicoesService::class.java)
        mapper = mock(ModelMapper::class.java)
    }

    @DisplayName("Lista encontrada deve ser coesa com a encontrada pela service")
    @Test
    fun listarRequisicoes() {
        val listaEsperada = requisicaoRepository.findAll()

        `when`(requisicaoRepository.findAll()).thenReturn(listaEsperada)

        val resultado = requisicaoService.listarRequisicoes()

        assertEquals(listaEsperada.size,resultado.size)

        assertEquals(listaEsperada,resultado)
    }

    @DisplayName("Lista deve ser mapeada corretamente para dto")
    @Test
    fun transformarListaEmDto() {

        val listaParam:List<Requisicoes> = requisicaoRepository.findAll()

        val listaEsperada:List<RequisicoesDoacaoResponseDTO> = listOf()

        val listaDto:MutableList<RequisicoesDoacaoResponseDTO> = mutableListOf()
        listaDto.forEachIndexed{index, atividade ->
            mapper.map(listaEsperada, RequisicoesDoacaoResponseDTO::class.java)
        }

        val resultado = requisicaoService.transformarListaEmDto(listaParam)

        assertEquals(listaDto,resultado)
    }

    @Test
    fun verificarLista() {
        val listaParam= emptyList<Requisicoes>()
        `when`(requisicaoService.verficarLista(listaParam)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException>{requisicaoService.verficarLista(listaParam)}

    }

    @Test
    fun getRequisicoesTrimestreTipo(){
        //val objVazio = RequisicaoDashDTO()
        //val casoInvalido = Caso("",1000,1000,1000)

        //`when`(requisicaoService.getRequisicoesTrimestreTipo()).thenReturn(objVazio)

        //val resultado = requisicaoService.getRequisicoesTrimestreTipo()

        //assertEquals(true,resultado is RequisicaoDashDTO)


        // Mock the repository responses
        //`when`(requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
        //    "cesta", 5, 1, 90)).thenReturn(10)
        //`when`(requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
        //   "cesta", 6, 1, 90)).thenReturn(20)
        // Add more mocks for each case...

        //val result = requisicaoService.getRequisicoesTrimestreTipo()

        // Verify the results
        //assertEquals(10, result.cesta_req[0])
        //assertEquals(20, result.cesta_cum[0])
        // Add more assertions for each expected result...
    }

    @DisplayName("Lança erro se algum campo do dto não for preenchido ")
    @Test
    fun validarDtoDash(){
        val objVazio = RequisicaoDashDTO()

        `when`(requisicaoService.validarDtoDash(objVazio)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException>{requisicaoService.validarDtoDash(objVazio)}
    }

    @DisplayName("Lança erro se o dto for null ")
    @Test
    fun validarDtoDash2(){
        val objVazio = null

        `when`(requisicaoService.validarDtoDash(objVazio)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException>{requisicaoService.validarDtoDash(objVazio)}
    }
}