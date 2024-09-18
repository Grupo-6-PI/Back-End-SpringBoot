package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso

data class UsuarioResponseLoginDTO(
    val id: Long,
    val nome: String,
    val email: String,
    val nivelAcesso: NivelAcesso
)