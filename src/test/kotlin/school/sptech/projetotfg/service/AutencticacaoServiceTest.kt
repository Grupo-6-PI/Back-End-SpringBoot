package school.sptech.projetotfg.service
import org.junit.jupiter.api.Assertions.*
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
    private lateinit var bairroRepository: BairroRepository
    private lateinit var cidadeRepository: CidadeRepository
    private lateinit var contatoRepository: ContatoRepository
    private lateinit var enderecoRepository: EnderecoRepository
    private lateinit var estadoRepository: EstadoRepository
    private lateinit var familiaRepository: FamiliaRepository
    private lateinit var identificadorRepository: IdentificadorRepository
    private lateinit var informacoesRepository: InformacoesAdicionaisRepository
    private lateinit var rendaRepository: RendaFamiliaRepository
    private lateinit var tamanhoCalcadoRepository: TamanhoCalcadoRepository
    private lateinit var tamanhoRoupaRepository: TamanhoRoupaRepository
    private lateinit var mapper: ModelMapper
    private lateinit var usuarioService: UsuarioService
    private lateinit var autenticacaoService: AutenticacaoService

    @BeforeEach
    fun instanciar() {
        usuarioRepository = mock(UsuarioRepository::class.java)
        acessoRepository = mock(AcessoRepository::class.java)
        situacaoRepository = mock(SituacaoRepository::class.java)
        loginRepository = mock(LoginRepository::class.java)
        nivelAcessoRepository = mock(NivelAcessoRepository::class.java)
        bairroRepository = mock(BairroRepository::class.java)
        cidadeRepository = mock(CidadeRepository::class.java)
        contatoRepository = mock(ContatoRepository::class.java)
        enderecoRepository = mock(EnderecoRepository::class.java)
        estadoRepository = mock(EstadoRepository::class.java)
        familiaRepository = mock(FamiliaRepository::class.java)
        identificadorRepository = mock(IdentificadorRepository::class.java)
        informacoesRepository = mock(InformacoesAdicionaisRepository::class.java)
        rendaRepository = mock(RendaFamiliaRepository::class.java)
        tamanhoCalcadoRepository = mock(TamanhoCalcadoRepository::class.java)
        tamanhoRoupaRepository = mock(TamanhoRoupaRepository::class.java)
        val dependenteRepository = mock(DependenteRepository::class.java)

        mapper = mock(ModelMapper::class.java)

        autenticacaoService = AutenticacaoService(
            usuarioRepository,
            acessoRepository,
            situacaoRepository,
            loginRepository,
            nivelAcessoRepository
        )
        usuarioService = UsuarioService(
            usuarioRepository,
            dependenteRepository,
            informacoesRepository,
            contatoRepository,
            enderecoRepository,
            estadoRepository,
            bairroRepository,
            cidadeRepository,
            familiaRepository,
            rendaRepository,
            tamanhoRoupaRepository,
            tamanhoCalcadoRepository,
            situacaoRepository,
            nivelAcessoRepository,
            identificadorRepository,
            mapper
        )
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