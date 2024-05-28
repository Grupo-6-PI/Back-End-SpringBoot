package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais
import school.sptech.projetotfg.domain.gerenciamento.Situacao
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso

data class UsuarioResponseDTO(
    val idUsuario: Long,
    val nome: String,
    val email: String,
    val informacoesAdicionais: InformacoesAdicionais,
    val situacao: Situacao,
    val nivelAcesso: NivelAcesso
)