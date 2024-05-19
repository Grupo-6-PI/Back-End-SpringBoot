package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.InformacoesAdicionais
import school.sptech.projetotfg.domain.NivelAcesso
import school.sptech.projetotfg.domain.Situacao

data class UsuarioResponseDTO(
    val idUsuario: Int,
    val nome: String,
    val email: String,
    val informacoesAdicionais: InformacoesAdicionais,
    val situacao: Situacao,
    val nivelAcesso: NivelAcesso
)