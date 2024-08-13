package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class InformacoesAdicionais (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:CPF private var cpf:String? = null,
    @field:Past private var dataNascimento:LocalDate? = null,
    @field:OneToOne private var endereco: Endereco? = null,
    @field:OneToOne private var familia: Familia? = null,
    @field:OneToOne private var identificador: Identificador? = null,
    @field:ManyToOne private var situacao: Situacao? = null,
    @field:PastOrPresent private var dataCriacao:LocalDateTime? = null,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime? = null,
    @field:Email @field:Size(max = 100) private var emailModificador:String? = null,
){
}