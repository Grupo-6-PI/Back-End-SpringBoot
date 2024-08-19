package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.*
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
class Atividade (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
    @field:NotBlank var nome:String?,
    private var horaComeco: LocalTime?,
    private var horaFinal:LocalTime?,
    @field:NotBlank var descricao:String?,
    @field:ManyToOne var tipoAtividade: TipoAtividade?,
    @field:PastOrPresent var dataCriacao: LocalDateTime?,
    @field:PastOrPresent var dataUltimaAtualizacao: LocalDateTime?,
    @field:Email @field:Size(max = 150) var emailModificador:String?
){



}