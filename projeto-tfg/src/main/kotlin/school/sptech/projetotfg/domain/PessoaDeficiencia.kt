package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class PessoaDeficiencia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idPessoaDeficiencia:Int,
    @field:Max(255) var verificacao:Int, //255 é o valor maximo de TINYINT
    @ManyToOne var deficiencia:Deficiencia,
    @field:PastOrPresent var dataCriacao: LocalDateTime,
    @field:PastOrPresent var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) var emailModificador:String
    ) {
}