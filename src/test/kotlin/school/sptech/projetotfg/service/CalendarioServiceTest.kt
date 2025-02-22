/*
package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.*
import school.sptech.projetotfg.dto.AtividadeDTO
import school.sptech.projetotfg.dto.CalendarioFiltroDTO
import school.sptech.projetotfg.dto.ReservaAtividadeResponseDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
class CalendarioServiceTest {

    private lateinit var atividadeRepository: AtividadeRepository
    private lateinit var reservaAtividadeRepository: ReservaAtividadeRepository
    private lateinit var tipoAtividadeRepository: TipoAtividadeRepository
    private lateinit var calendarioRepository: CalendarioRepository
    private lateinit var calendarioService: CalendarioService
    private lateinit var mapper: ModelMapper

    @BeforeEach
    fun instanciar() {
        atividadeRepository = mock(AtividadeRepository::class.java)
        reservaAtividadeRepository = mock(ReservaAtividadeRepository::class.java)
        tipoAtividadeRepository = mock(TipoAtividadeRepository::class.java)
        calendarioRepository = mock(CalendarioRepository::class.java)
        mapper = mock(ModelMapper::class.java)
        calendarioService = CalendarioService(atividadeRepository, reservaAtividadeRepository, tipoAtividadeRepository, calendarioRepository, mapper)
    }

    @Test
    fun `deve criar uma atividade com sucesso`() {
        // Configuração do mock para o Tipo de Atividade
        val tipoAtividade = TipoAtividade(1L, "Tipo Teste")
        `when`(tipoAtividadeRepository.findById(1L)).thenReturn(Optional.of(tipoAtividade))

        // Configuração do mock para o Calendário
        val calendario = Calendario(1L, 2024, 1, "Janeiro", 1, "Domingo")
        `when`(calendarioRepository.findById(anyLong())).thenReturn(Optional.of(calendario))
        `when`(calendarioRepository.getSemana(anyInt(), anyInt(), anyInt())).thenReturn(Optional.of(1L))

        // Configuração do mock para o Atividade
        val atividade = Atividade(null, "Teste", LocalTime.now(), LocalTime.now(), "Descrição Teste", tipoAtividade, LocalDateTime.now(), LocalDateTime.now(), "teste@teste.com")
        `when`(atividadeRepository.save(any(Atividade::class.java))).thenReturn(atividade)

        // Configuração do mock para o ReservaAtividade
        val reservaAtividade = ReservaAtividade(1L, null, null, LocalDateTime.now(), "teste@teste.com", calendario)
        `when`(reservaAtividadeRepository.save(any(ReservaAtividade::class.java))).thenReturn(reservaAtividade)

        val atividadeDTO = AtividadeDTO("Teste", LocalTime.now(), LocalTime.now(), "Descrição Teste", 1L, "teste@teste.com", CalendarioFiltroDTO(2024, 1, 1, "Domingo"))

        val resultado = calendarioService.createAtividade(atividadeDTO)

        assertNotNull(resultado)
        assertEquals(null, resultado.getAtividade()?.getNome())
        assertEquals("Domingo", resultado.getCalendario()?.getDiaNomeacao())
    }

    @Test
    fun `deve lancar excecao ao buscar reserva por id inexistente`() {
        `when`(reservaAtividadeRepository.existsById(1L)).thenReturn(false)

        val exception = assertThrows<ResponseStatusException> {
            calendarioService.getReservaById(1L)
        }

        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
    }

    @Test
    fun `deve atualizar uma reserva com sucesso`() {

        val calendario = Calendario(1, 2024, 1, "Janeiro", 1, "Segunda")
        val reservaAtividadeExistente = ReservaAtividade(1, null, null, LocalDateTime.now(), "original@teste.com", calendario)
        val reservaAtividadeAtualizada = ReservaAtividade(1, null, null, LocalDateTime.now(), "teste@teste.com", calendario)

        // Mock para o repositório
        `when`(reservaAtividadeRepository.existsById(1)).thenReturn(true)
        `when`(reservaAtividadeRepository.findById(1)).thenReturn(Optional.of(reservaAtividadeExistente))
        `when`(reservaAtividadeRepository.save(any(ReservaAtividade::class.java))).thenReturn(reservaAtividadeAtualizada)

        // Isso daqui ta dando erro de id, sei la o motivo
        val resultado = calendarioService.updateReserva(reservaAtividadeAtualizada)

        // Assert
        assertNotNull(resultado)
        assertEquals("teste@teste.com", resultado?.getEmailModificador())

        // Verifica se o repositório save foi chamado com a reserva atualizada
        verify(reservaAtividadeRepository).save(reservaAtividadeAtualizada)
    }



    @Test
    fun `deve deletar uma reserva com sucesso`() {
        `when`(reservaAtividadeRepository.existsById(1L)).thenReturn(true)

        calendarioService.deleteReserva(1L)

        verify(reservaAtividadeRepository, times(1)).deleteById(1L)
    }

    @Test
    fun `deve buscar o domingo da semana`() {
        val calendarioFiltroDTO = CalendarioFiltroDTO(2024, 1, 1, "Domingo")

        val calendario = Calendario(1L, 2024, 1, "Janeiro", 1, "Domingo")
        `when`(calendarioRepository.getSemana(anyInt(), anyInt(), anyInt())).thenReturn(Optional.of(1L))
        `when`(calendarioRepository.findById(anyLong())).thenReturn(Optional.of(calendario))

        val resultado = calendarioService.getDomingo(calendarioFiltroDTO)

        assertNotNull(resultado)
        assertEquals(7, resultado.size)
    }

    @Test
    fun `deve retornar null se dia nao encontrado`() {

        //erro no getDia também não entendi o motivo
        val semana = arrayOfNulls<Calendario>(7)
        val resultado = calendarioService.getDia("Domingo", semana)

        assertNull(resultado, "Isso aqui tem que dar null")
    }

}
*/