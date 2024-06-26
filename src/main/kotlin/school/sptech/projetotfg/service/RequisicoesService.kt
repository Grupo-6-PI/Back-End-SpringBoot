package school.sptech.projetotfg.service

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicaoDashDTO
import school.sptech.projetotfg.dto.RequisicoesCumResponseDTO
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.dto.RequisicoesReqResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository

@Service
class RequisicoesService (
    private val requisicaoRepository: RequisicaoRepository,
    private val mapper: ModelMapper,
){
    fun listarRequisicoes():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll()

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun transformarListaEmDto(listaRequisicoes: List<Requisicoes>):List<RequisicoesDoacaoResponseDTO>{

        val listaDto:MutableList<RequisicoesDoacaoResponseDTO> = mutableListOf()
        listaDto.forEachIndexed{index, atividade ->
            mapper.map(listaRequisicoes, RequisicoesDoacaoResponseDTO::class.java)
        }
        return listaDto
    }

    fun verficarLista(listaRequisicoes:List<Requisicoes>):Boolean{

        if(listaRequisicoes.isEmpty()){
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }
        return true
    }

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun findLimitedCum(): List<RequisicoesCumResponseDTO> {
        val query = entityManager.createQuery("""
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
    """, RequisicoesCumResponseDTO::class.java)
        query.maxResults = 1
        return query.resultList
    }

    fun findLimitedReq(): List<RequisicoesReqResponseDTO> {
        val query = entityManager.createQuery("""
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
    """, RequisicoesReqResponseDTO::class.java)
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

    fun validarDtoDash(dto: RequisicaoDashDTO?){
        if(dto==null) throw ResponseStatusException(HttpStatusCode.valueOf(404))
        if(dto.cesta_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.cesta_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.vestuario_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.vestuario_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.saude_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.saude_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.outro_req.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
        if(dto.outro_cum.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

}


//fun validarDtoDash(dto: RequisicaoDashDTO){
//if(dto==null) throw error("objeto não populado")
//if(dto.cesta_req.isEmpty()) throw error("cesta_req não populado")
//if(dto.cesta_cum.isEmpty()) throw error("cesta_cum não populado")
//if(dto.vestuario_req.isEmpty()) throw error("vestuario_req não populado")
//if(dto.vestuario_cum.isEmpty()) throw error("vestuario_cum não populado")
//if(dto.saude_req.isEmpty()) throw error("saude_req não populado")
//if(dto.saude_cum.isEmpty()) throw error("saude_cum não populado")
//if(dto.outro_req.isEmpty()) throw error("outro_req não populado")
//if(dto.outro_cum.isEmpty()) throw error("outro_cum não populado")
//}

//val casos : List<Caso> = listOf(
//            Caso("cesta",1,90,5),
//            Caso("cesta",1,90,6),
//            Caso("vestuario",1,90,5),
//            Caso("vestuario",1,90,6),
//            Caso("saude",1,90,5),
//            Caso("saude",1,90,6),
//            Caso("outro",1,90,5),
//            Caso("outro",1,90,6),
//
//            Caso("cesta",91,181,5),
//            Caso("cesta",91,181,6),
//            Caso("vestuario",91,181,5),
//            Caso("vestuario",91,181,6),
//            Caso("saude",91,181,5),
//            Caso("saude",91,181,6),
//            Caso("outro",91,181,5),
//            Caso("outro",91,181,6),
//
//            Caso("cesta",182,273,5),
//            Caso("cesta",182,273,6),
//            Caso("vestuario",182,273,5),
//            Caso("vestuario",182,273,6),
//            Caso("saude",182,273,5),
//            Caso("saude",182,273,6),
//            Caso("outro",182,273,5),
//            Caso("outro",182,273,6),
//
//            Caso("cesta",274,365,5),
//            Caso("cesta",274,365,6),
//            Caso("vestuario",274,365,5),
//            Caso("vestuario",274,365,6),
//            Caso("saude",274,365,5),
//            Caso("saude",274,365,6),
//            Caso("outro",274,365,5),
//            Caso("outro",274,365,6)
//        )

//for(i in 1..8){
//
//            if(agora.isBefore(incioTri2)){
//
//                for(c in 0..1){
//
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//                    if(situacao.toInt()==5) dto.cesta_req[0] = cesta
//                    else dto.cesta_cum[0] = cesta
//                }
//                for(c in 2..3){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.vestuario_req[0] = vestuario
//                    else dto.vestuario_cum[0] = vestuario
//                }
//                for(c in 4..5){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.saude_req[0] = saude
//                    else dto.saude_cum[0] = saude
//                }
//                for(c in 6..7){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.outro_req[0] = outro
//                    else dto.outro_cum[0] = outro
//                }
//            }
//            else if(agora.isBefore(inicioTri3) && agora.isAfter(fimTri1)){
//                for(c in 0..1){
//
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//                    if(situacao.toInt()==5) dto.cesta_req[1] = cesta
//                    else dto.cesta_cum[1] = cesta
//                }
//                for(c in 2..3){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.vestuario_req[1] = vestuario
//                    else dto.vestuario_cum[1] = vestuario
//                }
//                for(c in 4..5){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.saude_req[1] = saude
//                    else dto.saude_cum[1] = saude
//                }
//                for(c in 6..7){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.outro_req[1] = outro
//                    else dto.outro_cum[1] = outro
//                }
//            }
//            else if(agora.isBefore(inicioTri4) && agora.isAfter(fimTri2)){
//                for(c in 0..1){
//
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//                    if(situacao.toInt()==5) dto.cesta_req[2] = cesta
//                    else dto.cesta_cum[2] = cesta
//                }
//                for(c in 2..3){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.vestuario_req[2] = vestuario
//                    else dto.vestuario_cum[2] = vestuario
//                }
//                for(c in 4..5){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.saude_req[2] = saude
//                    else dto.saude_cum[2] = saude
//                }
//                for(c in 6..7){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.outro_req[2] = outro
//                    else dto.outro_cum[2] = outro
//                }
//            }
//            else{
//                for(c in 0..1){
//
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//                    if(situacao.toInt()==5) dto.cesta_req[3] = cesta
//                    else dto.cesta_cum[3] = cesta
//                }
//                for(c in 2..3){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.vestuario_req[3] = vestuario
//                    else dto.vestuario_cum[3] = vestuario
//                }
//                for(c in 4..5){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.saude_req[3] = saude
//                    else dto.saude_cum[3] = saude
//                }
//                for(c in 6..7){
//                    assunto = casos[c].assunto
//                    inicioTri = casos[c].inicioTri
//                    fimTri = casos[c].fimTri
//                    situacao = casos[c].situacao
//
//                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAssuntoAndSituacaoIdAndCalendarioIdBetween(
//                        assunto,
//                        situacao,
//                        inicioTri,
//                        fimTri
//                    )
//
//                    if(situacao.toInt()==5) dto.outro_req[3] = outro
//                    else dto.outro_cum[3] = outro
//                }
//            }
//        }
//        validarDtoDash(dto)
//        return dto
//    }

//val ano:Int = LocalDate.now().year
//        val agora: LocalDate = LocalDate.now()
//        val inicioTri1: LocalDate = LocalDate.of(ano,1,1)
//        val fimTri1: LocalDate = LocalDate.of(ano,3,31)
//        val incioTri2: LocalDate = LocalDate.of(ano,4,1)
//        val fimTri2: LocalDate = LocalDate.of(ano,6,30)
//        val inicioTri3: LocalDate = LocalDate.of(ano,7,1)
//        val fimTri3: LocalDate = LocalDate.of(ano,9,30)
//        val inicioTri4: LocalDate = LocalDate.of(ano,10,1)
//        val fimTri4: LocalDate = LocalDate.of(ano,12,31)
//        var assunto:String
//        var inicioTri:Long
//        var fimTri:Long
//        var situacao:Long
//        var dto:RequisicaoDashDTO = RequisicaoDashDTO()