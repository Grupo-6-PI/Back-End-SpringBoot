package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import java.time.*
import java.util.*

@Entity
class InformacoesAdicionais (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idInformacoesAdicionais:Int,
    @field:CPF var cpf:String,
    @field:Past var dataNascimento:Date,
    @OneToOne var endereco:Endereco,
    @OneToOne var familia:Familia,
    @OneToOne var identificador:Identificador,
    @OneToOne var contato:Contato,
    @ManyToOne var situacao:Situacao,
    @field:PastOrPresent var dataCriacao:LocalDateTime,
    @field:PastOrPresent var dataUltimaAtualizacao:LocalDateTime,
    @field:Email @field:Size(max = 100) var emailModificador:String
){
}