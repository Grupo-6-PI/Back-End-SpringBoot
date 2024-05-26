package school.sptech.projetotfg.dto

import java.time.LocalDateTime

data class BeneficiarioUpdateDTO(
    val idUsuario: Long,
    val nome: String?,
    val email: String?,
    val senha: String?,
    val cpf: String?,
    val dataNascimento: LocalDateTime?,
    val endereco: EnderecoDTO?,
    val contato: ContatoDTO?,
    val identificador: IdentificadorDTO?,
    val pessoaDeficiencia: PessoaDeficienciaDTO?,
    val quantidadePessoas: Int?,
    val rendaFamiliar: Double?,
    val urgenciaFamiliar: String?,
    val emailModificador: String
) {
}