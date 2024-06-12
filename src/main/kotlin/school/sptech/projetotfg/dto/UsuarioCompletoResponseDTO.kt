package school.sptech.projetotfg.dto

import jakarta.persistence.Id
import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso
import school.sptech.projetotfg.domain.gerenciamento.Situacao

class UsuarioCompletoResponseDTO(
    @field:Id
    var id: Long,
    var nome:String,
    var email:String,
    var senha:String?,
    var informacoesAdicionais: InformacoesAdicionais,
    var situacao: Situacao,
    var nivelAcesso: NivelAcesso
) {
}