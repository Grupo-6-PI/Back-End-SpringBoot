package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.*

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idUsuario: Int,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @OneToOne var informacoesAdicionais:InformacoesAdicionais,
    @ManyToOne var situacao: Situacao,
    @ManyToOne var nivelAcesso: NivelAcesso
) {
}