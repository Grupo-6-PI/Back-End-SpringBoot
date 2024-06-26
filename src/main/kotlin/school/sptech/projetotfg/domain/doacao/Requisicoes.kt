package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Requisicoes (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var nivelUrgencia: NivelUrgencia,
    @field:ManyToOne private var assuntoRequisicao: AssuntoRequisicao,
    @field:PastOrPresent private var dataCriacao:LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String,
    @field:ManyToOne private var usuario: Usuario,
    @field:ManyToOne private var situacao: Situacao,
    @field:ManyToOne private var calendario: Calendario
) {
}