package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.doacao.Requisicoes
import school.sptech.projetotfg.dto.RequisicaoDashDTO
import school.sptech.projetotfg.dto.RequisicaoDetalheResponseDTO
import school.sptech.projetotfg.dto.RequisicoesDoacaoResponseDTO
import school.sptech.projetotfg.repository.RequisicaoRepository
import java.time.LocalDate

@Service
class RequisicoesService (
    val requisicaoRepository: RequisicaoRepository,
    val mapper: ModelMapper
){
    fun listarRequisicoes():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll()

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesAceitas():List<RequisicoesDoacaoResponseDTO>{

        val listaRequisicoes = requisicaoRepository.findAll() // criar com nova modelagem um método na repositpry que busca baseado na requisição ter sido aceita ou não

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesNegadas():List<RequisicoesDoacaoResponseDTO>{
        val listaRequisicoes = requisicaoRepository.findAll()//criar consulta das negadas

        verficarLista(listaRequisicoes)

        return transformarListaEmDto(listaRequisicoes)
    }

    fun listarRequisicoesAbertas():List<RequisicoesDoacaoResponseDTO>{
        val listaRequisicoes = requisicaoRepository.findAll()//criar consulta das negadas

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
        if(!listaRequisicoes.isEmpty()){
            return true
        }
         throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }

    fun getDetalhesRequisicao(id:Long):RequisicaoDetalheResponseDTO{
        val requisicao = requisicaoRepository.findById(id)

        if(requisicao.isEmpty){
            val dto = mapper.map(requisicao, RequisicaoDetalheResponseDTO::class.java)

            return dto
        }

        throw ResponseStatusException(HttpStatusCode.valueOf(404))
    }

    fun getRequisicoesTrimestreTipo():RequisicaoDashDTO{
        val casos : List<Caso> = listOf(
            Caso("cesta",1,90,5),
            Caso("cesta",1,90,6),
            Caso("vestuario",1,90,5),
            Caso("vestuario",1,90,6),
            Caso("saude",1,90,5),
            Caso("saude",1,90,6),
            Caso("outro",1,90,5),
            Caso("outro",1,90,6),

            Caso("cesta",91,181,5),
            Caso("cesta",91,181,6),
            Caso("vestuario",91,181,5),
            Caso("vestuario",91,181,6),
            Caso("saude",91,181,5),
            Caso("saude",91,181,6),
            Caso("outro",91,181,5),
            Caso("outro",91,181,6),

            Caso("cesta",182,273,5),
            Caso("cesta",182,273,6),
            Caso("vestuario",182,273,5),
            Caso("vestuario",182,273,6),
            Caso("saude",182,273,5),
            Caso("saude",182,273,6),
            Caso("outro",182,273,5),
            Caso("outro",182,273,6),

            Caso("cesta",274,365,5),
            Caso("cesta",274,365,6),
            Caso("vestuario",274,365,5),
            Caso("vestuario",274,365,6),
            Caso("saude",274,365,5),
            Caso("saude",274,365,6),
            Caso("outro",274,365,5),
            Caso("outro",274,365,6)
        )

        val ano:Int = LocalDate.now().year
        val agora: LocalDate = LocalDate.now()

        val inicioTri1: LocalDate = LocalDate.of(ano,1,1)
        val fimTri1: LocalDate = LocalDate.of(ano,3,31)
        val incioTri2: LocalDate = LocalDate.of(ano,4,1)
        val fimTri2: LocalDate = LocalDate.of(ano,6,30)
        val inicioTri3: LocalDate = LocalDate.of(ano,7,1)
        val fimTri3: LocalDate = LocalDate.of(ano,9,31)
        val inicioTri4: LocalDate = LocalDate.of(ano,10,1)
        val fimTri4: LocalDate = LocalDate.of(ano,12,31)

        var assunto:String
        var inicioTri:Int
        var fimTri:Int
        var situacao:Int

        lateinit var dto:RequisicaoDashDTO

        for(i in 1..8){
            if(agora.isBefore(incioTri2)){
                for(c in 0..1){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)
                    if(situacao==5) dto.cesta_req.add(cesta)
                    else dto.cesta_cum.add(cesta)
                }
                for(c in 2..3){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.vestuario_req.add(vestuario)
                    else dto.vestuario_cum.add(vestuario)
                }
                for(c in 4..5){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.saude_req.add(saude)
                    else dto.saude_cum.add(saude)
                }
                for(c in 6..7){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.outro_req.add(outro)
                    else dto.outro_cum.add(outro)
                }
            }
            else if(agora.isBefore(inicioTri3) && agora.isAfter(fimTri1)){
                for(c in 0..1){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)
                    if(situacao==5) dto.cesta_req.add(cesta)
                    else dto.cesta_cum.add(cesta)
                }
                for(c in 2..3){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.vestuario_req.add(vestuario)
                    else dto.vestuario_cum.add(vestuario)
                }
                for(c in 4..5){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.saude_req.add(saude)
                    else dto.saude_cum.add(saude)
                }
                for(c in 6..7){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.outro_req.add(outro)
                    else dto.outro_cum.add(outro)
                }
            }
            else if(agora.isBefore(inicioTri4) && agora.isAfter(fimTri2)){
                for(c in 0..1){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)
                    if(situacao==5) dto.cesta_req.add(cesta)
                    else dto.cesta_cum.add(cesta)
                }
                for(c in 2..3){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.vestuario_req.add(vestuario)
                    else dto.vestuario_cum.add(vestuario)
                }
                for(c in 4..5){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.saude_req.add(saude)
                    else dto.saude_cum.add(saude)
                }
                for(c in 6..7){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.outro_req.add(outro)
                    else dto.outro_cum.add(outro)
                }
            }
            else{
                for(c in 0..1){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var cesta:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)
                    if(situacao==5) dto.cesta_req.add(cesta)
                    else dto.cesta_cum.add(cesta)
                }
                for(c in 2..3){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var vestuario:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.vestuario_req.add(vestuario)
                    else dto.vestuario_cum.add(vestuario)
                }
                for(c in 4..5){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var saude:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.saude_req.add(saude)
                    else dto.saude_cum.add(saude)
                }
                for(c in 6..7){
                    assunto = casos[c].assunto
                    inicioTri = casos[c].inicioTri
                    fimTri = casos[c].fimTri
                    situacao = casos[c].situacao

                    var outro:Int = requisicaoRepository.countByAssuntoRequisicaoAndCalendarioBetweenAndSituacao(assunto,inicioTri,fimTri,situacao)

                    if(situacao==5) dto.outro_req.add(outro)
                    else dto.outro_cum.add(outro)
                }
            }
        }
        validarDtoDash(dto)
        return dto
    }

    fun validarDtoDash(dto: RequisicaoDashDTO){
        if(dto==null) throw error("objeto não populado")
        if(dto.cesta_req.isEmpty()) throw error("cesta_req não populado")
        if(dto.cesta_cum.isEmpty()) throw error("cesta_cum não populado")
        if(dto.vestuario_req.isEmpty()) throw error("vestuario_req não populado")
        if(dto.vestuario_cum.isEmpty()) throw error("vestuario_cum não populado")
        if(dto.saude_req.isEmpty()) throw error("saude_req não populado")
        if(dto.saude_cum.isEmpty()) throw error("saude_cum não populado")
        if(dto.outro_req.isEmpty()) throw error("outro_req não populado")
        if(dto.outro_cum.isEmpty()) throw error("outro_cum não populado")
    }

}