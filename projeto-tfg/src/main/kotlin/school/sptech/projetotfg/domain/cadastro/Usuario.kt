package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    @field:OneToOne  private var informacoesAdicionaisId: InformacoesAdicionais,
    @field:ManyToOne private var usuarioSituacao: Situacao,
    @field:ManyToOne private var nivelAcessoId: NivelAcesso
) {
}