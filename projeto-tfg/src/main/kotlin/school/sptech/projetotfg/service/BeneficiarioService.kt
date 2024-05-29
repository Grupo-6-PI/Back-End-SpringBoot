package school.sptech.projetotfg.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.cadastro.*
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime

@Service
class BeneficiarioService(
    private val usuarioRepository: UsuarioRepository,
    private val informacoesAdicionaisRepository: InformacoesAdicionaisRepository,
    private val enderecoRepository: EnderecoRepository,
    private val bairroRepository: BairroRepository,
    private val cidadeRepository: CidadeRepository,
    private val contatoRepository: ContatoRepository,
    private val identificadorRepository: IdentificadorRepository,
    private val familiaRepository: FamiliaRepository,
    private val criancaRepository: CriancaRepository,
    private val pessoaDeficienciaRepository: PessoaDeficienciaRepository,
    private val tipoIdentificadorRepository: TipoIdentificadorRepository,
    private val deficienciaRepository: DeficienciaRepository,
    private val tipoContatoRepository: TipoContatoRepository,
    private val situacaoRepository: SituacaoRepository,
    private val nivelAcessoRepository: NivelAcessoRepository
) {

    @Transactional
    fun cadastrarBeneficiario(dto: BeneficiarioInputDTO): BeneficiarioResponseDTO {
        // Salvamento de Cidade e Bairro
        val cidade = cidadeRepository.findByNome(dto.endereco.cidade)
            ?: cidadeRepository.save(Cidade(nome = dto.endereco.cidade))

        val bairro = bairroRepository.findByNomeAndCidade(dto.endereco.bairro, cidade)
            ?: bairroRepository.save(Bairro(nome = dto.endereco.bairro, cidade = cidade))

        // Recuperando situação ativa
        val situacaoAtiva = situacaoRepository.findBySituacao("Ativo")
            .orElseThrow { IllegalArgumentException("Situacao 'Ativo' não encontrada") }

        // Salvamento de Endereço
        val endereco = Endereco(
            logradouro = dto.endereco.logradouro,
            numero = dto.endereco.numero,
            cep = dto.endereco.cep,
            bairro = bairro,
            situacao = situacaoAtiva
        )
        enderecoRepository.save(endereco)

        // Salvamento de Contato
        val contato = Contato(
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = dto.emailModificador
        )
        contatoRepository.save(contato)

        // Salvamento de Identificador
        val tipoIdentificador = tipoIdentificadorRepository.findByTipo(dto.identificador.tipo)
            ?: tipoIdentificadorRepository.save(
                TipoIdentificador(
                    tipo = dto.identificador.tipo,
                    situacao = situacaoAtiva
                )
            )


        val identificador = Identificador(
            numeracao = dto.identificador.numero,
            tipoIdentificador = tipoIdentificador,
            situacao = situacaoAtiva
        )
        identificadorRepository.save(identificador)

        // Salvamento de PessoaDeficiencia
        val deficiencia = deficienciaRepository.findByNomeDeficiencia(dto.pessoaDeficiencia.deficienciaNome)
            ?: deficienciaRepository.save(Deficiencia(nomeDeficiencia = dto.pessoaDeficiencia.deficienciaNome))

        val pessoaDeficiencia = PessoaDeficiencia(
            verificacao = dto.pessoaDeficiencia.verificacao,
            deficiencia = deficiencia,
            dataCriacao = dto.pessoaDeficiencia.dataCriacao,
            dataUltimaAtualizacao = dto.pessoaDeficiencia.dataUltimaAtualizacao,
            emailModificador = dto.pessoaDeficiencia.emailModificador
        )
        pessoaDeficienciaRepository.save(pessoaDeficiencia)

        // Salvamento de Familia e componentes
        val quantidadePessoas = QuantidadePessoas(
            minimo = dto.quantidadePessoas.toLong(),
            maximo = dto.quantidadePessoas.toLong(),
            situacao = situacaoAtiva
        )

        val rendaFamiliar = RendaFamiliar(
            renda = dto.rendaFamiliar,
            situacao = situacaoAtiva
        )

        val urgenciaFamiliar = UrgenciaFamiliar(
            descricao = dto.urgenciaFamiliar,
            situacao = situacaoAtiva
        )

        val familia = Familia(
            quantidadePessoas = quantidadePessoas,
            pessoaDeficiencia = pessoaDeficiencia,
            urgenciaFamiliar = urgenciaFamiliar,
            rendaFamiliar = rendaFamiliar,
            situacao = situacaoAtiva,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = dto.emailModificador
        )
        familiaRepository.save(familia)

        // Salvamento de Informações Adicionais
        val informacoesAdicionais = InformacoesAdicionais(
            cpf = dto.cpf,
            dataNascimento = dto.dataNascimento,
            endereco = endereco,
            contato = contato,
            identificador = identificador,
            familia = familia,
            situacao = situacaoAtiva,
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = dto.emailModificador
        )
        informacoesAdicionaisRepository.save(informacoesAdicionais)

        // Salvamento do Usuário
        val usuario = Usuario(
            nome = dto.nome,
            email = dto.email,
            senha = dto.senha,
            informacoesAdicionais = informacoesAdicionais,
            situacao = situacaoAtiva,
            nivelAcesso = nivelAcessoRepository.findByApelido("Usuário")
                ?: nivelAcessoRepository.save(NivelAcesso(apelido = "Usuário"))
        )
        usuarioRepository.save(usuario)

        return BeneficiarioResponseDTO(
            idUsuario = usuario.idUsuario,
            nome = usuario.nome,
            email = usuario.email
        )
    }

    fun obterBeneficiarioPorId(id: Long): Usuario? {
        return usuarioRepository.findById(id).orElse(null)
    }

    // Método para atualizar informações de um beneficiário

    @Transactional
    fun atualizarBeneficiario(id: Long, novoDTO: BeneficiarioInputDTO): BeneficiarioResponseDTO? {
        val beneficiarioExistente = usuarioRepository.findById(id).orElse(null) ?: return null

        // Atualize as informações do beneficiário existente com base nos novos dados fornecidos em novoDTO
        // ...

        // Salve as alterações
        val beneficiarioAtualizado = usuarioRepository.save(beneficiarioExistente)

        return BeneficiarioResponseDTO(
            idUsuario = beneficiarioAtualizado.idUsuario,
            nome = beneficiarioAtualizado.nome,
            email = beneficiarioAtualizado.email
        )
    }

    // Método para excluir um beneficiário

    fun excluirBeneficiario(id: Long): Boolean {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            return true
        }
        return false
    }
}
