/*
package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.*
import org.mockito.Mockito.*
import org.modelmapper.ModelMapper
import school.sptech.projetotfg.domain.atividades.Atividade
import school.sptech.projetotfg.domain.atividades.TipoAtividade
import school.sptech.projetotfg.dto.AtividadeResponseDTO
import school.sptech.projetotfg.repository.AtividadeRepository
import school.sptech.projetotfg.repository.TipoAtividadeRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import kotlin.NoSuchElementException

class AtividadeServiceTest {

    private val atividadeRepository: AtividadeRepository = mock()
    private val tipoAtividadeRepository: TipoAtividadeRepository = mock()
    private val modelMapper: ModelMapper = ModelMapper()

    private lateinit var atividadeService: AtividadeService

    @BeforeEach
    fun setUp() {
        atividadeService = AtividadeService(atividadeRepository, tipoAtividadeRepository, modelMapper)
    }

    @Test
    fun `listarAtivadade should return list of atividades`() {

        val atividades = listOf(
            Atividade(
                1,
                "Atividade 1",
                LocalTime.MIN,
                LocalTime.now(),
                "Descrição 1",
                TipoAtividade(
                    1,
                    "teste"
                ),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "paulo.cafasso@sptech.school"
            ),
            Atividade(
                2,
                "Atividade 2",
                LocalTime.MIN,
                LocalTime.now(),
                "Descrição 2",
                TipoAtividade(
                    1,
                    "teste"
                ),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "paulo.cafasso@sptech.school"
            )
        )

        `when`(atividadeRepository.findAll()).thenReturn(atividades)

        val result = atividadeService.listarAtivadade()

        assertNotNull(result)
        assertEquals(2, result.size)
        assertEquals("Atividade 1", result[0].getNome())
        verify(atividadeRepository).findAll()
    }
    @Test
    fun `atividadePorId should return AtividadeResponseDTO when atividade exists`() {
        val atividade = Atividade(
            1,
            "Atividade Teste",
            LocalTime.MIN,
            LocalTime.now(),
            "Descrição 1",
            TipoAtividade(
                1,
                "teste"
            ),
            LocalDateTime.now(),
            LocalDateTime.now(),
            "paulo.cafasso@sptech.school")
        `when`(atividadeRepository.findById(1)).thenReturn(Optional.of(atividade))

        val result: AtividadeResponseDTO = atividadeService.atividadePorId(1)

        assertNotNull(result)
        assertEquals(1, result.id)
        assertEquals("Atividade Teste", result.nome)
        verify(atividadeRepository).findById(1)
    }

    @Test
    fun `atividadePorId should throw exception when atividade does not exist`() {
        // Arrange
        `when`(atividadeRepository.findById(1)).thenReturn(Optional.empty())

        // Act & Assert
        val exception = assertThrows<NoSuchElementException> {
            atividadeService.atividadePorId(1)
        }
        assertEquals("No value present", exception.message)
        verify(atividadeRepository).findById(1)
    }
}
*/