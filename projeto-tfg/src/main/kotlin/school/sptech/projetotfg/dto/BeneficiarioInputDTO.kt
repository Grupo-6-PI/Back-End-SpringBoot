package school.sptech.projetotfg.dto

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import java.time.LocalDateTime

data class BeneficiarioInputDTO(
    val nome: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val dataNascimento: LocalDate,
    val endereco: EnderecoDTO,
    val identificador: IdentificadorDTO,
    val quantidadePessoas: Int,
    val rendaFamiliar: Double,
    val urgenciaFamiliar: String,
    val pessoaDeficiencia: PessoaDeficienciaDTO,
    val dataCriacao: LocalDateTime,
    val dataUltimaAtualizacao: LocalDateTime,
    val emailModificador: String
) {

}
