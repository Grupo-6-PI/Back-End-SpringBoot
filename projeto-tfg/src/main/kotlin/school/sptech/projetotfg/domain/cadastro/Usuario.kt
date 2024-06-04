package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @field:OneToOne private var informacoesAdicionaisId: InformacoesAdicionais?,
    @field:ManyToOne private var situacao: Situacao?,
    @field:ManyToOne private var nivelAcessoId: NivelAcesso?
) {

    constructor(
        paramId: Long,
        paramNome:String,
        paramEmail:String,
        paramSenha:String
    ):this(
        id = paramId,
        nome = paramNome,
        email = paramEmail,
        senha = paramSenha,
        informacoesAdicionaisId = null,
        situacao = null,
        nivelAcessoId = null
    )

}