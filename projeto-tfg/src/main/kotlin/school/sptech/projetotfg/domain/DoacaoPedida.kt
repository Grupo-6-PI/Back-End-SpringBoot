package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDateTime

@Entity
class DoacaoPedida(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idDoacaoPedida:Int,
    @ManyToOne var tipoDoacao:TipoDoacao,
    @ManyToOne var familia: Familia,
    @ManyToOne var situacao: Situacao,
    @field:PastOrPresent var dataCriacao: LocalDateTime,
    @field:PastOrPresent var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) var emailModificador:String
) {
}