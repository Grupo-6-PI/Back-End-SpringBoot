package school.sptech.projetotfg.service

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Dependente
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.repository.*
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.WeekFields
import java.util.concurrent.ArrayBlockingQueue

@Service
class RequisicoesService (
    private val requisicaoRepository: RequisicaoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val situacaoRepository: SituacaoRepository,
    private val assuntoRequisicaoRepository: AssuntoRequisicaoRepository,
    private val calendarioRepository: CalendarioRepository,
    private val tipoRequisicaoRepository: TipoRequisicaoRepository,
    private val requisicoesGraficoRepository: RequisicoesGraficoRepository,
    private val mapper: ModelMapper,
):school.sptech.projetotfg.complement.Service() {
    fun listarRequisicoes(id: Long): ArrayBlockingQueue<RequisicoesDTO> {

        val listaRequisicoes = requisicaoRepository.findAll()
        var resposta = mutableListOf<Requisicoes>()

        super.validarLista(listaRequisicoes)

        for (pedido in listaRequisicoes) {

            if (pedido.getUsuario()!!.getId() == id) {

                resposta.add(pedido)
            }

        }

        var pilhaResposta = ArrayBlockingQueue<RequisicoesDTO>(resposta.size)

        for (pedido in resposta.reversed()) {

            val rep = RequisicoesDTO(
                id = pedido.getId(),
                assuntoRequisicao = pedido.getAssuntoRequisicao(),
                situacao = pedido.getSituacao(),
                descricao = pedido.getDescricao()
            )

            pilhaResposta.add(rep)

        }

        return pilhaResposta

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

    fun getRequisicoesTrimestreTipo(): RequisicaoDashDTO {
        var dto = RequisicaoDashDTO()
        
        val data = requisicoesGraficoRepository.findAll().get(0)

        dto.cesta_req.add(data.cestas_T1_pedidas)
        dto.cesta_req.add(data.cestas_T2_pedidas)
        dto.cesta_req.add(data.cestas_T3_pedidas)
        dto.cesta_req.add(data.cestas_T4_pedidas)

        dto.cesta_cum.add(data.cestas_T1_cumpridas)
        dto.cesta_cum.add(data.cestas_T2_cumpridas)
        dto.cesta_cum.add(data.cestas_T3_cumpridas)
        dto.cesta_cum.add(data.cestas_T4_cumpridas)

        dto.vestuario_req.add(data.vestuario_T1_pedidas)
        dto.vestuario_req.add(data.vestuario_T2_pedidas)
        dto.vestuario_req.add(data.vestuario_T3_pedidas)
        dto.vestuario_req.add(data.vestuario_T4_pedidas)

        dto.vestuario_cum.add(data.vestuario_T1_cumpridas)
        dto.vestuario_cum.add(data.vestuario_T2_cumpridas)
        dto.vestuario_cum.add(data.vestuario_T3_cumpridas)
        dto.vestuario_cum.add(data.vestuario_T4_cumpridas)

        dto.saude_req.add(data.saude_T1_pedidas)
        dto.saude_req.add(data.saude_T2_pedidas)
        dto.saude_req.add(data.saude_T3_pedidas)
        dto.saude_req.add(data.saude_T4_pedidas)

        dto.saude_cum.add(data.saude_T1_cumpridas)
        dto.saude_cum.add(data.saude_T2_cumpridas)
        dto.saude_cum.add(data.saude_T3_cumpridas)
        dto.saude_cum.add(data.saude_T4_cumpridas)

        dto.outro_req.add(data.outros_T1_pedidas)
        dto.outro_req.add(data.outros_T2_pedidas)
        dto.outro_req.add(data.outros_T3_pedidas)
        dto.outro_req.add(data.outros_T4_pedidas)

        dto.outro_cum.add(data.outros_T1_cumpridas)
        dto.outro_cum.add(data.outros_T2_cumpridas)
        dto.outro_cum.add(data.outros_T3_cumpridas)
        dto.outro_cum.add(data.outros_T4_cumpridas)

        return dto
    }

    fun getRequisicoesSemanal(): RequisicoesGraficoSemanalDTO?{

        val data = LocalDateTime.now()

        val result = requisicaoRepository.getGraficoSemanal(data.monthValue, data.year)

        if (result != null && result.isNotEmpty()) {
            val row = result[0]
            return RequisicoesGraficoSemanalDTO(
                Cestas_S1_pedidas = (row[0] as? Number)?.toInt() ?: 0,
                Cestas_S1_cumpridas = (row[1] as? Number)?.toInt() ?: 0,
                Cestas_S2_pedidas = (row[2] as? Number)?.toInt() ?: 0,
                Cestas_S2_cumpridas = (row[3] as? Number)?.toInt() ?: 0,
                Cestas_S3_pedidas = (row[4] as? Number)?.toInt() ?: 0,
                Cestas_S3_cumpridas = (row[5] as? Number)?.toInt() ?: 0,
                Cestas_S4_pedidas = (row[6] as? Number)?.toInt() ?: 0,
                Cestas_S4_cumpridas = (row[7] as? Number)?.toInt() ?: 0,
                Cestas_S5_pedidas = (row[8] as? Number)?.toInt() ?: 0,
                Cestas_S5_cumpridas = (row[9] as? Number)?.toInt() ?: 0,
                Vestuario_S1_pedidas = (row[10] as? Number)?.toInt() ?: 0,
                Vestuario_S1_cumpridas = (row[11] as? Number)?.toInt() ?: 0,
                Vestuario_S2_pedidas = (row[12] as? Number)?.toInt() ?: 0,
                Vestuario_S2_cumpridas = (row[13] as? Number)?.toInt() ?: 0,
                Vestuario_S3_pedidas = (row[14] as? Number)?.toInt() ?: 0,
                Vestuario_S3_cumpridas = (row[15] as? Number)?.toInt() ?: 0,
                Vestuario_S4_pedidas = (row[16] as? Number)?.toInt() ?: 0,
                Vestuario_S4_cumpridas = (row[17] as? Number)?.toInt() ?: 0,
                Vestuario_S5_pedidas = (row[18] as? Number)?.toInt() ?: 0,
                Vestuario_S5_cumpridas = (row[19] as? Number)?.toInt() ?: 0,
                Saude_S1_pedidas = (row[20] as? Number)?.toInt() ?: 0,
                Saude_S1_cumpridas = (row[21] as? Number)?.toInt() ?: 0,
                Saude_S2_pedidas = (row[22] as? Number)?.toInt() ?: 0,
                Saude_S2_cumpridas = (row[23] as? Number)?.toInt() ?: 0,
                Saude_S3_pedidas = (row[24] as? Number)?.toInt() ?: 0,
                Saude_S3_cumpridas = (row[25] as? Number)?.toInt() ?: 0,
                Saude_S4_pedidas = (row[26] as? Number)?.toInt() ?: 0,
                Saude_S4_cumpridas = (row[27] as? Number)?.toInt() ?: 0,
                Saude_S5_pedidas = (row[28] as? Number)?.toInt() ?: 0,
                Saude_S5_cumpridas = (row[29] as? Number)?.toInt() ?: 0,
                Outros_S1_pedidas = (row[30] as? Number)?.toInt() ?: 0,
                Outros_S1_cumpridas = (row[31] as? Number)?.toInt() ?: 0,
                Outros_S2_pedidas = (row[32] as? Number)?.toInt() ?: 0,
                Outros_S2_cumpridas = (row[33] as? Number)?.toInt() ?: 0,
                Outros_S3_pedidas = (row[34] as? Number)?.toInt() ?: 0,
                Outros_S3_cumpridas = (row[35] as? Number)?.toInt() ?: 0,
                Outros_S4_pedidas = (row[36] as? Number)?.toInt() ?: 0,
                Outros_S4_cumpridas = (row[37] as? Number)?.toInt() ?: 0,
                Outros_S5_pedidas = (row[38] as? Number)?.toInt() ?: 0,
                Outros_S5_cumpridas = (row[39] as? Number)?.toInt() ?: 0
            )
        }

        return null

    }

    fun getRequisicoesDiario(): List<RequisicoesGraficoDiarioDTO>{

        val data = LocalDateTime.now()
        val semana = data.get(WeekFields.ISO.weekOfMonth())

        val result = requisicaoRepository.getGraficoDiarioPorSemana(data.monthValue, data.year, semana)

        return if (result != null && result.isNotEmpty()) {
            result.map { row ->
                RequisicoesGraficoDiarioDTO(
                    diaNumeracao = (row[0] as? Number)?.toInt() ?: 0,
                    diaNomeacao = row[1] as? String ?: "",
                    cestasPedidas = (row[2] as? Number)?.toInt() ?: 0,
                    cestasCumpridas = (row[3] as? Number)?.toInt() ?: 0,
                    vestuarioPedidas = (row[4] as? Number)?.toInt() ?: 0,
                    vestuarioCumpridas = (row[5] as? Number)?.toInt() ?: 0,
                    saudePedidas = (row[6] as? Number)?.toInt() ?: 0,
                    saudeCumpridas = (row[7] as? Number)?.toInt() ?: 0,
                    outrosPedidas = (row[8] as? Number)?.toInt() ?: 0,
                    outrosCumpridas = (row[9] as? Number)?.toInt() ?: 0
                )
            }
        } else {
            emptyList()
        }

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
    fun getQuantidadeRequisicoesNegadasUltimos30Dias(): Long?{
        val dataLimite = LocalDateTime.now().minusDays(30)
        return requisicaoRepository.getQuantidadeRequisicoesNegadasUltimos30Dias(dataLimite)
    }

    fun getQuantidadeTotalRequisicoesUltimos30Dias(): Long?{
        val dataLimite = LocalDateTime.now().minusDays(30)
        return requisicaoRepository.getQuantidadeTotalRequisicoesUltimos30Dias(dataLimite)
    }
}


