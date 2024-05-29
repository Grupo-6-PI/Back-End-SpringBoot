package school.sptech.projetotfg.domain.doacao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

@Entity
class Requisicoes (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var nivelUrgencia: NivelUrgencia,
    @field:ManyToOne private var assuntoRequisicaoId: AssuntoRequisicao,
    @field:PastOrPresent private var situacaoId: Situacao,
    @field:PastOrPresent private var dataCriacao:LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String,
    @field:ManyToOne private var usuario: Usuario
) {
}