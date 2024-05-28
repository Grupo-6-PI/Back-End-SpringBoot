package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idUsuario: Long = 0,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @field:OneToOne  private var informacoesAdicionais: InformacoesAdicionais,
    @field:ManyToOne private var situacao: Situacao,
    @field:ManyToOne private var nivelAcesso: NivelAcesso
) {
    fun getId():Long{
        return idUsuario
    }
    fun setId(novoId:Long){
        idUsuario = novoId
    }

    fun getInformacoesAdicionais(): InformacoesAdicionais {
        return informacoesAdicionais
    }

    fun setInformacoesAdicionais(novasInformacoesAdicionais: InformacoesAdicionais){
        informacoesAdicionais = novasInformacoesAdicionais
    }

    fun getSituacao(): Situacao {
        return situacao
    }

    fun setSituacao(novaSitucao: Situacao){
        situacao=novaSitucao
    }

    fun getNivelAcesso(): NivelAcesso {
        return nivelAcesso
    }

    fun setNivelAcesso(novoNivelAcesso: NivelAcesso){
        nivelAcesso=novoNivelAcesso
    }
}