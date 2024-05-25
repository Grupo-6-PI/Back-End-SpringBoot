package school.sptech.projetotfg.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import school.sptech.projetotfg.domain.cadastro.*
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.*
import java.time.LocalDateTime

@Service
class BeneficiarioService(
    private val usuarioRepository: UsuarioRepository,
    private val informacoesAdicionaisRepository: InformacoesAdicionaisRepository,
    private val enderecoRepository: EnderecoRepository,
    private val contatoRepository: ContatoRepository,
    private val identificadorRepository: IdentificadorRepository,
    private val familiaRepository: FamiliaRepository,
    private val criancaRepository: CriancaRepository
) {

    @Transactional
    fun cadastrarBeneficiario(dto: BeneficiarioInputDTO): BeneficiarioResponseDTO {
        // Mapeia EnderecoDTO para Endereco
        val endereco = Endereco(
            rua = dto.endereco.rua,
            numero = dto.endereco.numero,
            bairro = dto.endereco.bairro,
            cidade = dto.endereco.cidade,
            estado = dto.endereco.estado,
            cep = dto.endereco.cep
        )
        enderecoRepository.save(endereco)

        // Mapeia ContatoDTO para Contato
        val contato = Contato(
            telefone = dto.contato.telefone,
            celular = dto.contato.celular
        )
        contatoRepository.save(contato)

        // Mapeia IdentificadorDTO para Identificador
        val identificador = Identificador(
            tipo = dto.identificador.tipo,
            numero = dto.identificador.numero
        )
        identificadorRepository.save(identificador)

        // Mapeia FamiliaDTO para Familia
        val familia = Familia(
            nomeResponsavel = dto.familia.nomeResponsavel,
            parentesco = dto.familia.parentesco
        )
        familiaRepository.save(familia)

        // Mapeia CriancaDTO para Crianca
        val crianca = Crianca(
            nome = dto.crianca.nome,
            dataNascimento = dto.crianca.dataNascimento
        )
        criancaRepository.save(crianca)

        // Mapeia BeneficiarioInputDTO para InformacoesAdicionais
        val informacoesAdicionais = InformacoesAdicionais(
            cpf = dto.cpf,
            dataNascimento = dto.dataNascimento,
            endereco = endereco,
            contato = contato,
            identificador = identificador,
            familia = familia,
            situacao = null, // Adicione a lógica para setar a situação correta
            dataCriacao = dto.dataCriacao,
            dataUltimaAtualizacao = dto.dataUltimaAtualizacao,
            emailModificador = dto.emailModificador
        )
        informacoesAdicionaisRepository.save(informacoesAdicionais)

        // Mapeia BeneficiarioInputDTO para Usuario
        val usuario = Usuario(
            nome = dto.nome,
            email = dto.email,
            senha = dto.senha,
            informacoesAdicionais = informacoesAdicionais,
            situacao = null, // Adicione a lógica para setar a situação correta
            nivelAcesso = null // Adicione a lógica para setar o nível de acesso correto
        )
        val usuarioSalvo = usuarioRepository.save(usuario)

        return BeneficiarioResponseDTO(
            idUsuario = usuarioSalvo.getId(),
            nome = usuarioSalvo.getNome(),
            email = usuarioSalvo.getEmail()
        )
    }
}
