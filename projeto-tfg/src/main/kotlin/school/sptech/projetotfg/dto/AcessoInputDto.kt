package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.Situacao
import school.sptech.projetotfg.domain.Usuario

data class AcessoInputDto(
    val situacao: Situacao,
    val usuario: Usuario
)