package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class Contato(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field: NotBlank @field:NotNull private var informacoesContato:String?,
    @field:PastOrPresent private var dataCriacao:LocalDateTime?,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime?,
    @field:Email @field:Size(max = 150) private var emailModificador:String?,
    @ManyToOne @field: NotNull @field:NotBlank var tipoContato:TipoContato?,
    @ManyToOne @field: NotNull @field:NotBlank var informacoesAdicionais: InformacoesAdicionais?
    ) {
}