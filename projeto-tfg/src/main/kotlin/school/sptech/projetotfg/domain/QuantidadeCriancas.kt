package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
class QuantidadeCriancas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idQuantidadeCrianças:Int,
    @field:NotNull var quantidade:Int,
    @OneToOne var familia:Familia,
    @ManyToOne var situacao: Situacao,
    @field:PastOrPresent var dataCriacao: LocalDateTime,
    @field:PastOrPresent var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) var emailModificador:String
) {
}