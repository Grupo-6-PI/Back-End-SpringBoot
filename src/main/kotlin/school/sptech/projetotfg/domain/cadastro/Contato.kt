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
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long = 0,
    @field: NotBlank @field:NotNull private var informacoesContato:String? = null,
    @field:PastOrPresent private var dataCriacao:LocalDateTime? = null,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime? = null,
    @field:Email @field:Size(max = 150) private var emailModificador:String? = null,
    @ManyToOne @field: NotNull @field:NotBlank var tipoContato:TipoContato? = null,
    @ManyToOne @field: NotNull @field:NotBlank var informacoesAdicionais: InformacoesAdicionais? = null
    ) {
}