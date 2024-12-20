package school.sptech.projetotfg.service

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Dependente
import school.sptech.projetotfg.domain.doacao.AssuntoRequisicao
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Stack
import java.util.concurrent.ArrayBlockingQueue

@Service
class RequisicoesService (
    private val requisicaoRepository: RequisicaoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val situacaoRepository: SituacaoRepository,
    private val assuntoRequisicaoRepository: AssuntoRequisicaoRepository,
    private val calendarioRepository: CalendarioRepository,
    private val tipoRequisicaoRepository: TipoRequisicaoRepository,
    private val mapper: ModelMapper,
):school.sptech.projetotfg.complement.Service() {
    fun listarRequisicoes(id: Long): List<Requisicoes> {

        val listaRequisicoes = requisicaoRepository.findAll()
        var resposta = mutableListOf<Requisicoes>()

        super.validarLista(listaRequisicoes)

        for (pedido in listaRequisicoes) {

            if (pedido.getUsuario()!!.getId() == id) {
                resposta.add(pedido)
            }

        }

        return resposta

    }

    fun listarRequisicoesCanceladas(): ArrayBlockingQueue<RequisicaoResponseDTO> {

        val listaRequisicoes = requisicaoRepository.findAll()
        var resposta = mutableListOf<Requisicoes>()
        var id:Long = 7

        super.validarLista(listaRequisicoes)

        for (pedido in listaRequisicoes) {

            if (pedido.getSituacao()!!.getId() == id) {
                resposta.add(pedido)
            }

        }

        var pilhaResposta = ArrayBlockingQueue<RequisicaoResponseDTO>(resposta.size)

        for (pedido in resposta) {

            val numeracao:Int = resposta.indexOf(pedido)

            val solicitante:String = pedido.getUsuario()!!.getNome()!!

            val CPF:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getCpf()!!

            val logradouro:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getLogradouro()!!

            val estado:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getBairro()!!.getCidade()!!.getEstado()!!.getUf()!!

            val numero:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getNumero()!!;

            val endereco = "${logradouro}, ${numero} - ${estado}"

            val familiaOrigem:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getApelido()!!

            val quantidadePessoas:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes.size

            val possuiCrianca:Boolean = validarIdade(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val possuiPCD: Boolean = validarDef(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val rendaFamiliar:Double = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getRendaFamiliar()!!.getRenda()!!

            val tipoRequisicao:String = pedido.getAssuntoRequisicao()!!.getAssunto()!!

            val descricao:String = pedido.getDescricao()!!

            val dataNasc = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getDataNascimento()!!

            val newPedido = RequisicaoResponseDTO(
                id = pedido.getId()!!,
                numeracao = numeracao,
                solicitante = solicitante,
                CPF = CPF,
                dataNasc = dataNasc,
                endereco = endereco,
                familiaOrigem = familiaOrigem,
                quantidadePessoas = quantidadePessoas,
                possuiCrianca = possuiCrianca,
                possuiPCD = possuiPCD,
                rendaFamiliar = rendaFamiliar,
                tipoRequisicao = tipoRequisicao,
                descricao = descricao
            )

            pilhaResposta.add(newPedido)

        }

        return pilhaResposta

    }

    fun listarRequisicoesCumpridas(): ArrayBlockingQueue<RequisicaoResponseDTO> {

        val listaRequisicoes = requisicaoRepository.findAll()
        var resposta = mutableListOf<Requisicoes>()
        var id:Long = 6

        super.validarLista(listaRequisicoes)

        for (pedido in listaRequisicoes) {

            if (pedido.getSituacao()!!.getId() == id) {
                resposta.add(pedido)
            }

        }

        var pilhaResposta = ArrayBlockingQueue<RequisicaoResponseDTO>(resposta.size)

        for (pedido in resposta) {

            val numeracao:Int = resposta.indexOf(pedido)

            val solicitante:String = pedido.getUsuario()!!.getNome()!!

            val CPF:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getCpf()!!

            val logradouro:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getLogradouro()!!

            val estado:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getBairro()!!.getCidade()!!.getEstado()!!.getUf()!!

            val numero:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getNumero()!!;

            val endereco = "${logradouro}, ${numero} - ${estado}"

            val familiaOrigem:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getApelido()!!

            val quantidadePessoas:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes.size

            val possuiCrianca:Boolean = validarIdade(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val possuiPCD: Boolean = validarDef(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val rendaFamiliar:Double = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getRendaFamiliar()!!.getRenda()!!

            val tipoRequisicao:String = pedido.getAssuntoRequisicao()!!.getAssunto()!!

            val descricao:String = pedido.getDescricao()!!

            val dataNasc = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getDataNascimento()!!

            val newPedido = RequisicaoResponseDTO(
                id = pedido.getId()!!,
                numeracao = numeracao,
                solicitante = solicitante,
                CPF = CPF,
                dataNasc = dataNasc,
                endereco = endereco,
                familiaOrigem = familiaOrigem,
                quantidadePessoas = quantidadePessoas,
                possuiCrianca = possuiCrianca,
                possuiPCD = possuiPCD,
                rendaFamiliar = rendaFamiliar,
                tipoRequisicao = tipoRequisicao,
                descricao = descricao
            )

            pilhaResposta.add(newPedido)

        }

        return pilhaResposta

    }

    fun transformarListaEmDto(listaRequisicoes: List<Requisicoes>): List<RequisicoesDoacaoResponseDTO> {

        val listaDto: MutableList<RequisicoesDoacaoResponseDTO> = mutableListOf()
        listaDto.forEachIndexed { index, atividade ->
            mapper.map(listaRequisicoes, RequisicoesDoacaoResponseDTO::class.java)
        }
        return listaDto
    }

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun findLimitedCum(): List<RequisicoesCumResponseDTO> {
        val query = entityManager.createQuery(
            """
        SELECT NEW school.sptech.projetotfg.dto.RequisicoesCumResponseDTO(
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 6),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 6)
        )
        FROM Requisicoes r WHERE r.calendario.id = 1
    """, RequisicoesCumResponseDTO::class.java
        )
        query.maxResults = 1
        return query.resultList
    }

    fun findLimitedReq(): List<RequisicoesReqResponseDTO> {
        val query = entityManager.createQuery(
            """
        SELECT NEW school.sptech.projetotfg.dto.RequisicoesReqResponseDTO(
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 1 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 2 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 3 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5),
        (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 1 AND 90
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 91 AND 181
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 182 AND 273
                AND r.situacao.id = 5),
            (SELECT COUNT(r) FROM Requisicoes r 
                WHERE r.assuntoRequisicao.id = 4 
                AND r.calendario.id BETWEEN 274 AND 365
                AND r.situacao.id = 5)
        ) FROM Requisicoes r WHERE r.id = 1 
    """, RequisicoesReqResponseDTO::class.java
        )
        query.maxResults = 1
        return query.resultList
    }

    fun getRequisicoesTrimestreTipo(): RequisicaoDashDTO {
        var dto = RequisicaoDashDTO()
        var dadosReq = findLimitedReq().get(0)
        var dadosCum = findLimitedCum().get(0)

        dto.cesta_req.add(dadosReq!!.cesta_req01)
        dto.cesta_req.add(dadosReq!!.cesta_req02)
        dto.cesta_req.add(dadosReq!!.cesta_req03)
        dto.cesta_req.add(dadosReq!!.cesta_req04)

        dto.cesta_cum.add(dadosCum!!.cesta_cum01)
        dto.cesta_cum.add(dadosCum!!.cesta_cum02)
        dto.cesta_cum.add(dadosCum!!.cesta_cum03)
        dto.cesta_cum.add(dadosCum!!.cesta_cum04)

        dto.vestuario_req.add(dadosReq!!.vestuario_req01)
        dto.vestuario_req.add(dadosReq!!.vestuario_req02)
        dto.vestuario_req.add(dadosReq!!.vestuario_req03)
        dto.vestuario_req.add(dadosReq!!.vestuario_req04)

        dto.vestuario_cum.add(dadosCum!!.vestuario_cum01)
        dto.vestuario_cum.add(dadosCum!!.vestuario_cum02)
        dto.vestuario_cum.add(dadosCum!!.vestuario_cum03)
        dto.vestuario_cum.add(dadosCum!!.vestuario_cum04)

        dto.saude_req.add(dadosReq!!.saude_req01)
        dto.saude_req.add(dadosReq!!.saude_req02)
        dto.saude_req.add(dadosReq!!.saude_req03)
        dto.saude_req.add(dadosReq!!.saude_req04)

        dto.saude_cum.add(dadosCum!!.saude_cum01)
        dto.saude_cum.add(dadosCum!!.saude_cum02)
        dto.saude_cum.add(dadosCum!!.saude_cum03)
        dto.saude_cum.add(dadosCum!!.saude_cum04)

        dto.outro_req.add(dadosReq!!.outro_req01)
        dto.outro_req.add(dadosReq!!.outro_req02)
        dto.outro_req.add(dadosReq!!.outro_req03)
        dto.outro_req.add(dadosReq!!.outro_req04)

        dto.outro_cum.add(dadosCum!!.outros_cum01)
        dto.outro_cum.add(dadosCum!!.outros_cum02)
        dto.outro_cum.add(dadosCum!!.outros_cum03)
        dto.outro_cum.add(dadosCum!!.outros_cum04)

        return dto
    }

    fun validarDtoDash(dto: RequisicaoDashDTO?) {
        if (dto == null) throw ResponseStatusException(HttpStatusCode.valueOf(404))
        if (dto.cesta_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.cesta_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.vestuario_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.vestuario_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.saude_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.saude_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.outro_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if (dto.outro_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

    fun saveRequisicao(requisicao: RequisicaoResquestDTO): Requisicoes {

        super.validarId(requisicao.assuntoId, assuntoRequisicaoRepository)

        super.validarId(requisicao.usuarioId, usuarioRepository)

        val assunto = assuntoRequisicaoRepository.findById(requisicao.assuntoId)

        val usuario = usuarioRepository.findById(requisicao.usuarioId)

        val calendario = calendarioRepository.findByAnoAndMesNumeracaoAndDiaNumeracao(
            requisicao.data!!.ano,
            requisicao.data!!.mesNumeracao,
            requisicao.data!!.diaNumeracao
        )

        val situacao = situacaoRepository.findById(5)

        val email = requisicao.emailModificador

        val requisicaoMap = Requisicoes(
            id = null,
            assuntoRequisicao = assunto.get(),
            dataCriacao = LocalDateTime.now(),
            dataUltimaAtualizacao = LocalDateTime.now(),
            emailModificador = email,
            usuario = usuario.get(),
            situacao = situacao.get(),
            calendario = calendario.get(),
            descricao = requisicao.descricao
        )

        val requisicaoSalva = requisicaoRepository.save(requisicaoMap)

        return requisicaoSalva

    }

    fun getRequisicao(id: Long): Requisicoes {

        super.validarId(id, requisicaoRepository)

        var requisicao = requisicaoRepository.findById(id)

        return requisicao.get()

    }

    fun listarTipoRequisicao(): List<TipoRequisicaoDTO> {
        val tipoRequisicao = tipoRequisicaoRepository.findAll()
        return tipoRequisicao.map { tipoRequisicao ->
            TipoRequisicaoDTO(
                id = tipoRequisicao.getId()!!,
                assunto = tipoRequisicao.getAssunto()!!
            )
        }
    }


    fun alterarSituacao(id: Long, novaSituacao: Int): Requisicoes {
        val requisicao = requisicaoRepository.findById(id).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Requisição não encontrada")
        }

        var situacao:Situacao

            when (novaSituacao) {
                1 -> { situacao = situacaoRepository.findById(6).get() }
                2 -> { situacao = situacaoRepository.findById(7).get() }
                3 -> { situacao = situacaoRepository.findById(5).get() }
                else -> throw IllegalArgumentException("Situação inválida")
            }

            requisicao.setSituacao(situacao)
            requisicao.setDataUltimaAtualizacao(LocalDateTime.now())
            requisicaoRepository.save(requisicao)

        return requisicao
    }

    fun listRequisicaoADM():ArrayBlockingQueue<RequisicaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll()
        var resposta = mutableListOf<Requisicoes>()
        var id:Long = 5

        super.validarLista(listaRequisicoes)

        for (pedido in listaRequisicoes) {

            if (pedido.getSituacao()!!.getId() == id) {
                resposta.add(pedido)
            }

        }

        var pilhaResposta = ArrayBlockingQueue<RequisicaoResponseDTO>(resposta.size)

        for (pedido in resposta) {

            val numeracao:Int = resposta.indexOf(pedido)

            val solicitante:String = pedido.getUsuario()!!.getNome()!!

            val CPF:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getCpf()!!

            val logradouro:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getLogradouro()!!

            val estado:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getBairro()!!.getCidade()!!.getEstado()!!.getUf()!!

            val numero:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getEndereco()!!.getNumero()!!;

            val endereco = "${logradouro}, ${numero} - ${estado}"

            val familiaOrigem:String = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getApelido()!!

            val quantidadePessoas:Int = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes.size

            val possuiCrianca:Boolean = validarIdade(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val possuiPCD: Boolean = validarDef(pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.dependentes)

            val rendaFamiliar:Double = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getFamilia()!!.getRendaFamiliar()!!.getRenda()!!

            val tipoRequisicao:String = pedido.getAssuntoRequisicao()!!.getAssunto()!!

            val descricao:String = pedido.getDescricao()!!

            val dataNasc = pedido.getUsuario()!!.getInformacoesAdicionais()!!.getDataNascimento()!!

            val newPedido = RequisicaoResponseDTO(
                id = pedido.getId()!!,
                numeracao = numeracao,
                solicitante = solicitante,
                CPF = CPF,
                dataNasc = dataNasc,
                endereco = endereco,
                familiaOrigem = familiaOrigem,
                quantidadePessoas = quantidadePessoas,
                possuiCrianca = possuiCrianca,
                possuiPCD = possuiPCD,
                rendaFamiliar = rendaFamiliar,
                tipoRequisicao = tipoRequisicao,
                descricao = descricao
            )

            pilhaResposta.add(newPedido)

        }

        return pilhaResposta

    }

    fun validarIdade(dependentes: MutableList<Dependente>):Boolean{

        for (pessoa in dependentes){

            var idade = LocalDate.now().year - pessoa.getDataNascimento()!!.year

            if (idade < 18){

                return true

            }

        }

        return false

    }

    fun validarDef(dependentes: MutableList<Dependente>):Boolean{

        for (pessoa in dependentes){

            if (pessoa.getDeficiente()!!){
                return true
            }

        }

        return false

    }

}


