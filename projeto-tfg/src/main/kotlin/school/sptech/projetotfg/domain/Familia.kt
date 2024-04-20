package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
class Familia(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idFamilia:Int,
    @OneToOne var quantidadePessoas:QuantidadePessoas,
    @OneToOne var pessoaDeficiencia:PessoaDeficiencia,
    @OneToOne var urgenciaFamiliar:UrgenciaFamiliar,
    @OneToOne var rendaFamiliar:RendaFamiliar,
    @ManyToOne var situacao:Situacao,
    @field:PastOrPresent var dataCriacao: LocalDateTime,
    @field:PastOrPresent var dataUltimaAtualizacao: LocalDateTime,
    @field:Email @field:Size(max = 150) var emailModificador:String
) {
}