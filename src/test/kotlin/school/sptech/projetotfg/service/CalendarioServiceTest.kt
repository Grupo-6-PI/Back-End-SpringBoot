package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.*
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class CalendarioServiceTest {

    @Mock
    private lateinit var atividadeRepository: AtividadeRepository

    @Mock
    private lateinit var calendarioRepository: CalendarioRepository

    @Mock
    private lateinit var reservaAtividadeRepository: ReservaAtividadeRepository

    @Mock
    private lateinit var tipoAtividadeRepository: TipoAtividadeRepository

    @InjectMocks
    private lateinit var calendarioService: CalendarioService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `deve criar atividade com sucesso`() {
        val atividadeDTO = AtividadeDTO(
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividadeId = 1L,
            emailModificador = "teste@example.com"
        )

        val calendarioFiltroDTO = CalendarioFiltroDTO(
            ano = 2024,
            mesNumeracao = 6,
            diaNumeracao = 15
        )

        val tipoAtividade = TipoAtividade(
            id = 1L,
            tipo = "Tipo de Teste"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SATURDAY"
        )

        val atividade = Atividade(
            id = 1L,
            nome = atividadeDTO.nome,
            horaComeco = atividadeDTO.horaComeco,
            horaFinal = atividadeDTO.horaFinal,
            descricao = atividadeDTO.descricao,
            tipoAtividade = tipoAtividade,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = atividadeDTO.emailModificador
        )

        Mockito.`when`(tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)).thenReturn(Optional.of(tipoAtividade))
        Mockito.`when`(calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(
            calendarioFiltroDTO.ano, calendarioFiltroDTO.mesNumeracao, calendarioFiltroDTO.diaNumeracao
        )).thenReturn(Optional.of(calendario))
        Mockito.`when`(atividadeRepository.save(Mockito.any(Atividade::class.java))).thenReturn(atividade)
        Mockito.`when`(reservaAtividadeRepository.save(Mockito.any(ReservaAtividade::class.java))).thenReturn(reservaAtividade)

        val result = calendarioService.createAtividade(atividadeDTO, calendarioFiltroDTO)

        assertNotNull(result)
        assertEquals(reservaAtividade.getId(), result.getId())
        assertEquals(reservaAtividade.atividade.id, result.atividade.id)
        assertEquals(reservaAtividade.getCalendario().getId(), result.getCalendario().getId())
    }

    @Test
    fun `deve retornar erro ao tentar criar atividade com tipo de atividade inexistente`() {
        val atividadeDTO = AtividadeDTO(
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividadeId = 1L,
            emailModificador = "teste@example.com"
        )

        val calendarioFiltroDTO = CalendarioFiltroDTO(
            ano = 2024,
            mesNumeracao = 6,
            diaNumeracao = 15
        )

        Mockito.`when`(tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)).thenReturn(Optional.empty())

        val exception = assertThrows<IllegalArgumentException> {
            calendarioService.createAtividade(atividadeDTO, calendarioFiltroDTO)
        }

        assertEquals("Tipo de Atividade não encontrado", exception.message)
    }

    @Test
    fun `deve retornar erro ao tentar criar atividade com calendario inexistente`() {
        val atividadeDTO = AtividadeDTO(
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividadeId = 1L,
            emailModificador = "teste@example.com"
        )

        val calendarioFiltroDTO = CalendarioFiltroDTO(
            ano = 2024,
            mesNumeracao = 6,
            diaNumeracao = 15
        )

        val tipoAtividade = TipoAtividade(
            id = 1L,
            tipo = "Tipo de Teste"
        )

        Mockito.`when`(tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)).thenReturn(Optional.of(tipoAtividade))
        Mockito.`when`(calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(
            calendarioFiltroDTO.ano, calendarioFiltroDTO.mesNumeracao, calendarioFiltroDTO.diaNumeracao
        )).thenReturn(Optional.empty())

        val exception = assertThrows<IllegalArgumentException> {
            calendarioService.createAtividade(atividadeDTO, calendarioFiltroDTO)
        }

        assertEquals("Calendário não encontrado para a data fornecida", exception.message)
    }

    @Test
    fun `deve recuperar todas as reservas com sucesso`() {
        val atividade = Atividade(
            id = 1L,
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividade = TipoAtividade(id = 1L, tipo = "Tipo de Teste"),
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SÁBADO"
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        Mockito.`when`(reservaAtividadeRepository.findAll()).thenReturn(listOf(reservaAtividade))

        val result = calendarioService.getAllReserva()

        assertNotNull(result)
        assertTrue(result.sabado.contains(reservaAtividade))
    }

    @Test
    fun `deve recuperar reserva por ID com sucesso`() {
        val atividade = Atividade(
            id = 1L,
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividade = TipoAtividade(id = 1L, tipo = "Tipo de Teste"),
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SÁBADO"
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        Mockito.`when`(reservaAtividadeRepository.findById(reservaAtividade.getId())).thenReturn(Optional.of(reservaAtividade))

        val result = calendarioService.getReservaById(reservaAtividade.getId())

        assertNotNull(result)
        assertEquals(reservaAtividade.getId(), result?.getId())
    }

    @Test
    fun `deve retornar nulo ao tentar recuperar reserva inexistente por ID`() {
        Mockito.`when`(reservaAtividadeRepository.findById(1L)).thenReturn(Optional.empty())

        val result = calendarioService.getReservaById(1L)

        assertNull(result)
    }

    @Test
    fun `deve atualizar reserva com sucesso`() {
        val atividadeDTO = AtividadeDTO(
            nome = "Atividade Atualizada",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição Atualizada",
            tipoAtividadeId = 1L,
            emailModificador = "teste@example.com"
        )

        val tipoAtividade = TipoAtividade(
            id = 1L,
            tipo = "Tipo de Teste"
        )

        val atividade = Atividade(
            id = 1L,
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividade = tipoAtividade,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SÁBADO"
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        Mockito.`when`(reservaAtividadeRepository.findById(reservaAtividade.getId())).thenReturn(Optional.of(reservaAtividade))
        Mockito.`when`(tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)).thenReturn(Optional.of(tipoAtividade))
        Mockito.`when`(atividadeRepository.save(Mockito.any(Atividade::class.java))).thenReturn(atividade)
        Mockito.`when`(reservaAtividadeRepository.save(Mockito.any(ReservaAtividade::class.java))).thenReturn(reservaAtividade)

        val result = calendarioService.updateReserva(reservaAtividade.getId(), atividadeDTO)

        assertNotNull(result)
        assertEquals(atividadeDTO.nome, result?.atividade?.nome)
        assertEquals(atividadeDTO.descricao, result?.atividade?.descricao)
    }

    @Test
    fun `deve retornar erro ao tentar atualizar reserva com tipo de atividade inexistente`() {
        val atividadeDTO = AtividadeDTO(
            nome = "Atividade Atualizada",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição Atualizada",
            tipoAtividadeId = 1L,
            emailModificador = "teste@example.com"
        )

        val atividade = Atividade(
            id = 1L,
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividade = TipoAtividade(id = 1L, tipo = "Tipo de Teste"),
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SÁBADO"
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        Mockito.`when`(reservaAtividadeRepository.findById(reservaAtividade.getId())).thenReturn(Optional.of(reservaAtividade))
        Mockito.`when`(tipoAtividadeRepository.findById(atividadeDTO.tipoAtividadeId)).thenReturn(Optional.empty())

        val exception = assertThrows<IllegalArgumentException> {
            calendarioService.updateReserva(reservaAtividade.getId(), atividadeDTO)
        }

        assertEquals("Tipo de Atividade não encontrado", exception.message)
    }

    @Test
    fun `deve excluir reserva com sucesso`() {
        val atividade = Atividade(
            id = 1L,
            nome = "Atividade de Teste",
            horaComeco = LocalDateTime.now().toLocalTime(),
            horaFinal = LocalDateTime.now().toLocalTime(),
            descricao = "Descrição de Teste",
            tipoAtividade = TipoAtividade(id = 1L, tipo = "Tipo de Teste"),
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        val calendario = Calendario(
            id = 1L,
            ano = 2024L,
            mesNumeracao = 6,
            mesNomeacao = "JUNE",
            diaNumeracao = 15,
            diaNomeacao = "SÁBADO"
        )

        val reservaAtividade = ReservaAtividade(
            id = 1L,
            atividade = atividade,
            calendario = calendario,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "teste@example.com"
        )

        Mockito.`when`(reservaAtividadeRepository.existsById(reservaAtividade.getId())).thenReturn(true)
        Mockito.doNothing().`when`(reservaAtividadeRepository).deleteById(reservaAtividade.getId())

        assertDoesNotThrow { calendarioService.deleteReserva(reservaAtividade.getId()) }
    }

    @Test
    fun `deve retornar erro ao tentar excluir reserva inexistente`() {
        Mockito.`when`(reservaAtividadeRepository.existsById(1L)).thenReturn(false)

        val exception = assertThrows<ResponseStatusException> {
            calendarioService.deleteReserva(1L)
        }

        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Atividade não encontrada", exception.reason)
    }
}
