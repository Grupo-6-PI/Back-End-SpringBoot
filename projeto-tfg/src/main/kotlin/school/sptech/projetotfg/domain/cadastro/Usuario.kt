package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idUsuario: Int = 0,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @OneToOne  private var informacoesAdicionais: InformacoesAdicionais,
    @ManyToOne private var situacao: Situacao,
    @ManyToOne private var nivelAcesso: NivelAcesso
) {
    fun getId():Int{
        return idUsuario
    }
    fun setId(novoId:Int){
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