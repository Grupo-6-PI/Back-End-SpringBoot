package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.*
import java.time.LocalDateTime

@Entity
class Atividade (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long = 0,
    @field:NotBlank @field:Max(100) private var nome:String,
    @field:PastOrPresent private var horaComeco:LocalDateTime,
    @field:PastOrPresent private var horaFinal:LocalDateTime,
    @field:NotBlank @field:Max(150) private var descricao:String,
    @field:ManyToOne private var tipoAtividade: TipoAtividade,
    @field:PastOrPresent private var dataCriacao: LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150)
    private var emailModificador:String
){
}