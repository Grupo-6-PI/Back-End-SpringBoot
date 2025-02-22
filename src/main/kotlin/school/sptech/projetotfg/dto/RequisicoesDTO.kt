package school.sptech.projetotfg.dto

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import school.sptech.projetotfg.domain.atividades.Calendario
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.doacao.AssuntoRequisicao
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDateTime

class RequisicoesDTO(
    var id: Long?,
    var assuntoRequisicao: AssuntoRequisicao?,
    var situacao: Situacao?,
    var descricao:String?
)