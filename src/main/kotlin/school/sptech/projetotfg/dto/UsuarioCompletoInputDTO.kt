package school.sptech.projetotfg.dto

import jakarta.persistence.*
import school.sptech.projetotfg.domain.cadastro.Contato
import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

class UsuarioCompletoInputDTO(
    var nome:String?,
    var email:String?,
    var senha:String?,
    var informacoesAdicionais: InformacoesAdicionais?,
    var situacao: Situacao?,
    var nivelAcesso: NivelAcesso?,
    var contato: Contato?
) {

}