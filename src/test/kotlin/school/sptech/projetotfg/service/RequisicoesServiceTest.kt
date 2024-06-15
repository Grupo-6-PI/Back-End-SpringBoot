package school.sptech.projetotfg.service

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.modelmapper.ModelMapper
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.doacao.AssuntoRequisicao
import school.sptech.projetotfg.domain.doacao.NivelUrgencia
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository
import java.time.LocalDateTime
import kotlin.random.Random

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
        val listaParam= mutableListOf<Requisicoes>()
        val excecao:ResponseStatusException = assertThrows(ResponseStatusException::class.java) {
            requisicaoService.verficarLista(listaParam)
        }
        assertEquals(204,excecao.statusCode.value())
    }

    @Test
    fun getDetalhesRequisicao() {
    }

    @Test
    fun getRequisicoesTrimestreTipo(){

    }
}