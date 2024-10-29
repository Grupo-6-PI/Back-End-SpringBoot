package school.sptech.projetotfg.dto

import java.time.LocalDate

data class CadastroMassaDTO(
    var nome: String,
    var email: String,
    var senha: String,
    var cpf: String,
    var dataNascimento: LocalDate,
    var logradouro: String,
    var numero: Int,
    var cep: String,
    var bairro_nome: String,
    var cidade_nome: String,
    var estado_nome: String,
    var estado_uf: String,
    var familia_apelido: String,
    var familia_quantidadePessoas: Int,
    var rendaFamiliar_renda: Double,
    var identificador_numeracao: String

)