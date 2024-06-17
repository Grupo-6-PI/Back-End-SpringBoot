package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.SQLInsert
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
@SQLInsert(sql = "INSERT INTO usuario(email,informacoes_adicionais_id,nivel_acesso_id,nome,senha,situacao_id) VALUES (?,?,?,?,AES_ENCRYPT(?,'chave'),?)" )
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @field:OneToOne var informacoesAdicionais: InformacoesAdicionais?,
    @field:ManyToOne var situacao: Situacao?,
    @field:ManyToOne var nivelAcesso: NivelAcesso?
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
        informacoesAdicionais = null,
        situacao = null,
        nivelAcesso = null
    )

    constructor(
        paramNome:String,
        paramEmail:String,
        paramSenha:String
    ):this(
        id = null,
        nome = paramNome,
        email = paramEmail,
        senha = paramSenha,
        informacoesAdicionais = null,
        situacao = null,
        nivelAcesso = null
    )

    constructor(
        paramId: Long,
        paramNome:String,
        paramEmail:String,
        paramSenha:String,
        paramInformacoesAdicionais: InformacoesAdicionais,
        paramSituacao: Situacao,
        paramNivelAcesso: NivelAcesso
    ):this(
        id = paramId,
        nome = paramNome,
        email = paramEmail,
        senha = paramSenha,
        informacoesAdicionais = paramInformacoesAdicionais,
        situacao = paramSituacao,
        nivelAcesso = paramNivelAcesso
    )

    fun getId(): Long? {
        return id
    }

}