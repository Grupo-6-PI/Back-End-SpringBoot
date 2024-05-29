package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class ReservaAtividade(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:ManyToOne private var calendario: Calendario,
    @field:ManyToOne private var atividade: Atividade,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) private var emailModificador:String
) {
}