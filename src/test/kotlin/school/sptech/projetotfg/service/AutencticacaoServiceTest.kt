/*import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.modelmapper.ModelMapper
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.gerenciamento.Acesso
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.LoginRequestDTO
import school.sptech.projetotfg.dto.UsuarioInputDTO
import school.sptech.projetotfg.dto.UsuarioResponseLoginDTO
import school.sptech.projetotfg.repository.*
import school.sptech.projetotfg.service.AutenticacaoService
import school.sptech.projetotfg.service.UsuarioService
import java.time.LocalDate
import java.util.*

class AutenticacaoServiceTest {

    private lateinit var usuarioRepository: UsuarioRepository
    private lateinit var acessoRepository: AcessoRepository
    private lateinit var situacaoRepository: SituacaoRepository
    private lateinit var loginRepository: LoginRepository
    private lateinit var nivelAcessoRepository: NivelAcessoRepository
    private lateinit var autenticacaoService: AutenticacaoService
    private lateinit var usuarioService: UsuarioService
    private lateinit var mapper: ModelMapper

    @BeforeEach
    fun instanciar() {
        usuarioRepository = mock(UsuarioRepository::class.java)
        acessoRepository = mock(AcessoRepository::class.java)
        situacaoRepository = mock(SituacaoRepository::class.java)
        loginRepository = mock(LoginRepository::class.java)
        nivelAcessoRepository = mock(NivelAcessoRepository::class.java)

        autenticacaoService = AutenticacaoService(
            usuarioRepository,
            acessoRepository,
            situacaoRepository,
            loginRepository,
            nivelAcessoRepository
        )
        usuarioService = UsuarioService(usuarioRepository, mapper)
    }

    @Test
    fun `login - usuario inexistente deve lançar exceção`() {
        // Arrange
        val request = LoginRequestDTO("email@invalido.com", "senha123")
        `when`(usuarioRepository.existsByEmail(request.email)).thenReturn(false)

        // Act & Assert
        val exception = assertThrows(ResponseStatusException::class.java) {
            autenticacaoService.login(request)
        }

        assertEquals("Usuário inexistente, por favor realize o cadastro antes do login", exception.reason)
    }

    @Test
    fun `login - senha incorreta deve lançar exceção`() {
        // Arrange
        val request = LoginRequestDTO("email@valido.com", "senhaErrada")
        val usuario = Usuario(1L, "Usuário", "email@valido.com", "senha123")
        `when`(usuarioRepository.existsByEmail(request.email)).thenReturn(true)
        `when`(usuarioRepository.buscarEmail(request.email)).thenReturn(usuario)

        // Act & Assert
        val exception = assertThrows(ResponseStatusException::class.java) {
            autenticacaoService.login(request)
        }

        assertEquals("Senha incorreta", exception.reason)
    }

}

*/