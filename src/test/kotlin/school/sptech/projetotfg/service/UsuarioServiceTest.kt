/*
package school.sptech.projetotfg.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseDTO
import school.sptech.projetotfg.repository.UsuarioRepository
import java.util.Optional

class UsuarioServiceTest {

    private lateinit var usuarioRepository: UsuarioRepository
    private lateinit var mapper: ModelMapper
    private lateinit var usuarioService: UsuarioService

    @BeforeEach
    fun instanciar() {
        usuarioRepository = mock(UsuarioRepository::class.java)
        mapper = mock(ModelMapper::class.java)
        usuarioService = UsuarioService(usuarioRepository, mapper)
    }

    @Test
    fun `deve cadastrar usuario com sucesso`() {
        val usuarioInputDTO = UsuarioInputDTO("Nome Teste", "teste@teste.com", "senha123")
        val usuarioSalvo = Usuario(1, "Nome Teste", "teste@teste.com", "senha123")

        // Mock
        `when`(usuarioRepository.existsByEmail(usuarioInputDTO.email)).thenReturn(false)
        `when`(usuarioRepository.save(any(Usuario::class.java))).thenReturn(usuarioSalvo)

        // Execução
        val usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioInputDTO)

        // Verificações
        assertNotNull(usuarioResponseDTO)
        assertEquals(usuarioSalvo.getId(), usuarioResponseDTO.id)
        assertEquals(usuarioSalvo.getNome(), usuarioResponseDTO.nome)
        assertEquals(usuarioSalvo.getEmail(), usuarioResponseDTO.email)
    }

    @Test
    fun `deve lançar exceção ao cadastrar usuario com email já existente`() {
        val usuarioInputDTO = UsuarioInputDTO("Nome Teste", "teste@teste.com", "senha123")

        // Mock
        `when`(usuarioRepository.existsByEmail(usuarioInputDTO.email)).thenReturn(true)

        // Execução e Verificação
        val exception = assertThrows<ResponseStatusException> {
            usuarioService.cadastrarUsuario(usuarioInputDTO)
        }
        assertEquals(HttpStatus.BAD_REQUEST, exception.statusCode)
        assertEquals("Email já cadastrado", exception.reason)
    }

    @Test
    fun `deve atualizar usuario com sucesso`() {
        val usuarioInputDTO = UsuarioInputDTO("Nome Atualizado", "atualizado@teste.com", "novaSenha")
        val usuarioExistente = Usuario(1, "Nome Antigo", "antigo@teste.com", "senhaAntiga")

        // Mock
        `when`(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioExistente))
        `when`(usuarioRepository.save(any(Usuario::class.java))).thenReturn(usuarioExistente)

        // Execução
        val usuarioResponseDTO = usuarioService.atualizarUsuario(1, usuarioInputDTO)

        // Verificações
        assertNotNull(usuarioResponseDTO)
        assertEquals(1, usuarioResponseDTO.id)
        assertEquals("Nome Atualizado", usuarioResponseDTO.nome)
        assertEquals("atualizado@teste.com", usuarioResponseDTO.email)
    }

    @Test
    fun `deve lançar exceção ao atualizar usuario que não existe`() {
        val usuarioInputDTO = UsuarioInputDTO("Nome Atualizado", "atualizado@teste.com", "novaSenha")

        // Mock
        `when`(usuarioRepository.findById(1)).thenReturn(Optional.empty())

        // Execução e Verificação
        val exception = assertThrows<ResponseStatusException> {
            usuarioService.atualizarUsuario(1, usuarioInputDTO)
        }
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Usuário não encontrado", exception.reason)
    }

    @Test
    fun `deve excluir usuario com sucesso`() {
        // Mock
        `when`(usuarioRepository.existsById(1)).thenReturn(true)
        doNothing().`when`(usuarioRepository).deleteById(1)

        // Execução
        usuarioService.excluirUsuario(1)

        // Verificação
        verify(usuarioRepository, times(1)).deleteById(1)
    }

    @Test
    fun `deve lançar exceção ao excluir usuario que não existe`() {
        // Mock
        `when`(usuarioRepository.existsById(1)).thenReturn(false)

        // Execução e Verificação
        val exception = assertThrows<ResponseStatusException> {
            usuarioService.excluirUsuario(1)
        }
        assertEquals(HttpStatus.NOT_FOUND, exception.statusCode)
        assertEquals("Usuário não encontrado", exception.reason)
    }

    @Test
    fun `deve listar usuarios com sucesso`() {
        val usuarios = listOf(
            Usuario(1, "Nome 1", "email1@teste.com", "senha1"),
            Usuario(2, "Nome 2", "email2@teste.com", "senha2")
        )

        // Mock
        `when`(usuarioRepository.findAll()).thenReturn(usuarios)

        // Execução
        val usuarioResponseDTOs = usuarioService.listarUsuarios()

        // Verificação
        assertNotNull(usuarioResponseDTOs)
        assertEquals(2, usuarioResponseDTOs.size)
        assertEquals("Nome 1", usuarioResponseDTOs[0].nome)
        assertEquals("email1@teste.com", usuarioResponseDTOs[0].email)
        assertEquals("Nome 2", usuarioResponseDTOs[1].nome)
        assertEquals("email2@teste.com", usuarioResponseDTOs[1].email)
    }
}
*/