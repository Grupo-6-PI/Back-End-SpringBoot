package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.UsuarioRepository
import java.util.*

@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private lateinit var usuarioRepository: UsuarioRepository

    @Mock
    private lateinit var mapper: ModelMapper

    @InjectMocks
    private lateinit var usuarioService: UsuarioService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `deve cadastrar usuario com sucesso`() {
        val usuarioInputDTO = UsuarioInputDTO(
            nome = "Teste",
            email = "teste@example.com",
            senha = "123456"
        )

        val usuario = Usuario(
            id = 1L,
            nome = "Teste",
            email = "teste@example.com",
            senha = "123456",
            informacoesAdicionais = null,
            situacao = null,
            nivelAcesso = null
        )

        Mockito.`when`(usuarioRepository.existsByEmail(usuarioInputDTO.email)).thenReturn(false)
        Mockito.`when`(usuarioRepository.save(Mockito.any(Usuario::class.java))).thenReturn(usuario)

        val result = usuarioService.cadastrarUsuario(usuarioInputDTO)

        assertNotNull(result)
        assertEquals(usuario.getId(), result.id)
        assertEquals(usuario.nome, result.nome)
        assertEquals(usuario.email, result.email)
    }

    @Test
    fun `deve retornar erro ao tentar cadastrar usuario com email ja existente`() {
        val usuarioInputDTO = UsuarioInputDTO(
            nome = "Teste",
            email = "teste@example.com",
            senha = "123456"
        )

        Mockito.`when`(usuarioRepository.existsByEmail(usuarioInputDTO.email)).thenReturn(true)

        val exception = assertThrows<ResponseStatusException> {
            usuarioService.cadastrarUsuario(usuarioInputDTO)
        }

        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
        assertEquals("Email jÃ¡ cadastrado", exception.reason)
    }
}