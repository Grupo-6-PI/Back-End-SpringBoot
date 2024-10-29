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
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @field:NotBlank private var nome:String?,
    @field:Email private var email:String?,
    @field:NotBlank private var senha:String?,
    @field:OneToOne private var informacoesAdicionais: InformacoesAdicionais?,
    @field:ManyToOne private var situacao: Situacao?,
    @field:ManyToOne private var nivelAcesso: NivelAcesso?
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

    fun getId():Long?{
        return id
    }

    fun getNome():String?{
        return nome
    }

    fun getEmail():String?{
        return email
    }

    fun getSenha():String?{
        return senha
    }

    fun getInformacoesAdicionais():InformacoesAdicionais?{
        return informacoesAdicionais
    }

    fun getSituacao():Situacao?{
        return situacao
    }

    fun getNivelAcesso():NivelAcesso?{
        return nivelAcesso
    }

    fun setNome(new: String?){
       this.nome = new
    }

    fun setEmail(new: String?){
        this.email = new
    }

    fun setSenha(new: String?){
        this.senha = new
    }

    fun setInformacoesAdicionais(new: InformacoesAdicionais?){
        this.informacoesAdicionais = new
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

    fun setNivelAcesso(new: NivelAcesso?){
        this.nivelAcesso = new
    }

}