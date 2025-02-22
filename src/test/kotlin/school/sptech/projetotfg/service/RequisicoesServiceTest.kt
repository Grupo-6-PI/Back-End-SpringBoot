/*
package school.sptech.projetotfg.service

import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.hibernate.validator.internal.util.Contracts.assertTrue
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.modelmapper.ModelMapper
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.doacao.AssuntoRequisicao
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime
import java.util.*

class RequisicoesServiceTest {

    fun mockRequisicoes(id: Long): Requisicoes {
        val mockAssuntoRequisicao = mock(AssuntoRequisicao::class.java)
        val mockUsuario = mock(Usuario::class.java)
        val mockSituacao = mock(Situacao::class.java)
        val mockCalendario = mock(Calendario::class.java)

        return Requisicoes(
            id = id,
            assuntoRequisicao = mockAssuntoRequisicao,
            dataCriacao = LocalDateTime.now().minusDays(10),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = "exemploemail@email.com",
            usuario = mockUsuario,
            situacao = mockSituacao,
            calendario = mockCalendario,
            descricao = ""
        )
    }

    private val requisicaoRepository: RequisicaoRepository = mock(RequisicaoRepository::class.java)
    private val modelMapper: ModelMapper = ModelMapper()
    private val service = RequisicoesService(
        requisicaoRepository,
        mock(UsuarioRepository::class.java),
        mock(SituacaoRepository::class.java),
        mock(AssuntoRequisicaoRepository::class.java),
        mock(CalendarioRepository::class.java),
        mock(TipoRequisicaoRepository::class.java),
        modelMapper
    )

    @Test
    fun `listarRequisicoes deve retornar lista de DTOs corretamente`() {
        // Arrange
        val requisicoesList = mutableListOf(mockRequisicoes(1), mockRequisicoes(2))

        `when`(requisicaoRepository.findAll()).thenReturn(requisicoesList)

        // Act
        val result = service.listarRequisicoes(5)

        // Assert
        assertNotNull(result)
        assertTrue(result.isEmpty(), "Lista Vazia")
    }

    @Test
    fun `listarRequisicoes deve lançar exceção quando a lista estiver vazia`() {
        // Arrange
        `when`(requisicaoRepository.findAll()).thenReturn(mutableListOf<Requisicoes>())

        // Act & Assert
        assertThrows(ResponseStatusException::class.java) {
            service.listarRequisicoes(5)
        }
    }

    @Test
    fun `transformarListaEmDto deve mapear Requisicoes para RequisicoesDoacaoResponseDTO`() {
        // Arrange
        val requisicoesList = mutableListOf<Requisicoes>(mockRequisicoes(1))

        // Act
        val result = service.transformarListaEmDto(requisicoesList)

        // Assert
        assertNotNull(result)
    }
}
*/