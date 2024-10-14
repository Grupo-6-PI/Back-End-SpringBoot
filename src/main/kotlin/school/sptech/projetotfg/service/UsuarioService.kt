package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.*
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.*

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val dependenteRepository: DependenteRepository,
    private val informacoesRepository: InformacoesAdicionaisRepository,
    private val contatoRepository: ContatoRepository,
    private val enderecoRepostiory: EnderecoRepository,
    private val estadoRepostiory: EstadoRepository,
    private val bairroRepostiory: BairroRepository,
    private val cidadeRepository: CidadeRepository,
    private val familiaRepository: FamiliaRepository,
    private val rendaRepository: RendaFamiliaRepository,
    private val tamanhoRoupaRepository: TamanhoRoupaRepository,
    private val tamanhoCalcadoRepository: TamanhoCalcadoRepository,
    private val mapper: ModelMapper
) :school.sptech.projetotfg.complement.Service(){
    fun cadastrarUsuario(usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        if (usuarioRepository.existsByEmail(usuarioInputDTO.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado")
        }

        val usuario = mapper.map(usuarioInputDTO,Usuario::class.java)

        return try {
            val usuarioSalvo = usuarioRepository.save(usuario)
            UsuarioResponseDTO(
                id = usuarioSalvo.getId()!!,
                nome = usuarioSalvo.getNome()!!,
                email = usuarioSalvo.getEmail()!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun atualizarUsuario(id: Long, usuarioInputDTO: UsuarioInputDTO): UsuarioResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.setNome(usuarioInputDTO.nome)
        usuarioExistente.setEmail(usuarioInputDTO.email)
        usuarioExistente.setSenha(usuarioInputDTO.senha)

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioResponseDTO(
                id = usuarioAtualizado.getId()!!,
                nome = usuarioAtualizado.getNome()!!,
                email = usuarioAtualizado.getEmail()!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuarios(): List<UsuarioResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioResponseDTO(
                id = usuario.getId()!!,
                nome = usuario.getNome()!!,
                email = usuario.getEmail()!!
            )
        }
    }

    fun excluirUsuario(id: Long) {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {
            usuarioRepository.deleteById(id)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir usuário: ${ex.message}")
        }
    }

    fun encontrarUsuario(id: Long): UsuarioResponseDTO {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {

            val usuario = usuarioRepository.findById(id)
            val resposta = mapper.map(
                usuario,
                UsuarioResponseDTO::class.java
            )
            return resposta

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao encontrar o usuário: ${ex.message}")
        }
    }

    fun separarArqs(json: CadastroCompletoInputDTO):UsuarioCompletoResponseDTO{

        val usuario = cadastrarUsuarioCompleto(json.usuario!!)

        if(json.dependentes != null){
            cadastrarDependentes(json.dependentes!!, usuario.informacoesAdicionais.getFamilia()!!)
        }

        return usuario

    }

    fun cadastrarUsuarioCompleto(usuarioCompletoInputDTO: UsuarioCompletoInputDTO): UsuarioCompletoResponseDTO {

//        if (usuarioRepository.existsByEmail(usuarioCompletoInputDTO!!.email!!)) {
//            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado")
//        }

        val usuario = mapper.map(usuarioCompletoInputDTO,Usuario::class.java)

        try {

            val cidade = cidadeRepository.save(usuario.getInformacoesAdicionais()!!.getEndereco()!!.getBairro()!!.getCidade()!!)

            val bairro = bairroRepostiory.save(usuario.getInformacoesAdicionais()!!.getEndereco()!!.getBairro()!!)

            bairro.setCidade(cidade)
            val endereco = enderecoRepostiory.save(usuario.getInformacoesAdicionais()!!.getEndereco()!!)
            endereco.setBairro(bairro)

            val renda = rendaRepository.save(usuario.getInformacoesAdicionais()!!.getFamilia()!!.getRendaFamiliar()!!)

            usuario.getInformacoesAdicionais()!!.setFamilia(familiaRepository.save(usuario.getInformacoesAdicionais()!!.getFamilia()!!))
            usuario.getInformacoesAdicionais()!!.getFamilia()!!.setRendaFamiliar(renda)
            val informacoes = informacoesRepository.save(usuario.getInformacoesAdicionais()!!)
            informacoes.setEndereco(endereco)
            val contato = usuarioCompletoInputDTO.contato!!

            contato.setInformacoesAdicionais(informacoes)

            contatoRepository.save(contato)

            val usuarioSalvo = usuarioRepository.save(usuario)
            return UsuarioCompletoResponseDTO(
                id = usuarioSalvo.getId()!!,
                nome = usuarioSalvo.getNome()!!,
                email = usuarioSalvo.getEmail()!!,
                senha = null,
                informacoesAdicionais = informacoes,
                situacao = usuarioSalvo.getSituacao()!!,
                nivelAcesso = usuarioSalvo.getNivelAcesso()!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar usuário: ${ex.message}")
        }
    }

    fun cadastrarDependentes(array:MutableList<DependenteInputDTO>,familia:Familia) {

        for (a in array){

            val depen = mapper.map(a,Dependente::class.java)

            depen.setFamilia(familia)

            val tamanhoRoupa:TamanhoRoupa = tamanhoRoupaRepository.save(depen.getTamanhoRoupa()!!)

            val tamanhoCalcado:TamanhoCalcado = tamanhoCalcadoRepository.save(depen.getTamanhoCalcado()!!)

            depen.setTamanhoRoupa(tamanhoRoupa)
            depen.setTamanhoCalcado(tamanhoCalcado)

            dependenteRepository.save(depen)

        }

    }

    fun getCidades():MutableList<Cidade>{

        val cidades = cidadeRepository.findAll()

        super.validarLista(cidades)

        return cidades

    }

    fun getBairro():MutableList<Bairro>{

        val bairros = bairroRepostiory.findAll()

        super.validarLista(bairros)

        return bairros

    }

    fun atualizarUsuarioCompleto(id: Long, usuarioCompletoInputDTO: UsuarioCompletoInputDTO): UsuarioCompletoResponseDTO {
        val usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado") }

        usuarioExistente.setNome(usuarioCompletoInputDTO.nome)
        usuarioExistente.setEmail(usuarioCompletoInputDTO.email)
        usuarioExistente.setSenha(usuarioCompletoInputDTO.senha)
        usuarioExistente.setInformacoesAdicionais(usuarioCompletoInputDTO.informacoesAdicionais!!)
        usuarioExistente.setSituacao(usuarioCompletoInputDTO.situacao!!)
        usuarioExistente.setNivelAcesso(usuarioCompletoInputDTO.nivelAcesso!!)

        return try {
            val usuarioAtualizado = usuarioRepository.save(usuarioExistente)
            UsuarioCompletoResponseDTO(
                id = usuarioAtualizado.getId()!!,
                nome = usuarioAtualizado.getNome()!!,
                email = usuarioAtualizado.getEmail()!!,
                senha = usuarioAtualizado.getSenha(),
                informacoesAdicionais = usuarioAtualizado.getInformacoesAdicionais()!!,
                situacao = usuarioAtualizado.getSituacao()!!,
                nivelAcesso = usuarioAtualizado.getNivelAcesso()!!
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar usuário: ${ex.message}")
        }
    }

    fun listarUsuariosCompletos(): List<UsuarioCompletoResponseDTO> {
        val usuarios = usuarioRepository.findAll()
        return usuarios.map { usuario ->
            UsuarioCompletoResponseDTO(
                id = usuario.getId()!!,
                nome = usuario.getNome()!!,
                email = usuario.getEmail()!!,
                senha = null,
                informacoesAdicionais = usuario.getInformacoesAdicionais()!!,
                situacao = usuario.getSituacao()!!,
                nivelAcesso = usuario.getNivelAcesso()!!
            )
        }
    }

    fun encontrarUsuarioCompleto(id: Long): UsuarioCompletoResponseDTO {
        if (!usuarioRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        }
        try {

            val usuario = usuarioRepository.findById(id)
            val resposta = mapper.map(
                usuario,
                UsuarioCompletoResponseDTO::class.java
            )
            return resposta

        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao encontrar o usuário: ${ex.message}")
        }
    }

}
