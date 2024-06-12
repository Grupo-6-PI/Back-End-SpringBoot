package school.sptech.projetotfg.domain.atividades

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.doacao.Requisicoes
import java.time.LocalDateTime

@Entity
class DisponibilidadeUsuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var requisicoes:Requisicoes,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String,
    @field:PastOrPresent private var horaComeco:LocalDateTime,
    @field:PastOrPresent private var horaFinal:LocalDateTime,
    @field:ManyToOne private var calendario:Calendario
){
}