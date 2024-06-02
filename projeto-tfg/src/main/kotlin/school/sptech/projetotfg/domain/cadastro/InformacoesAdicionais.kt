package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import java.time.*

@Entity
class InformacoesAdicionais (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long = 0,
    @field:CPF private var cpf:String,
    @field:Past private var dataNascimento:LocalDate,
    @field:OneToOne private var endereco: Endereco,
    @field:OneToOne private var familia: Familia,
    @field:OneToOne private var identificador: Identificador,
    @field:ManyToOne private var situacao: Situacao,
    @field:PastOrPresent private var dataCriacao:LocalDateTime,
    @field:PastOrPresent private var dataUltimaAtualizacao:LocalDateTime,
    @field:Email @field:Size(max = 100) private var emailModificador:String
){
}