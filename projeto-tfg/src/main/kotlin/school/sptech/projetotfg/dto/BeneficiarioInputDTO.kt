package school.sptech.projetotfg.dto

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import java.time.LocalDateTime

data class BeneficiarioInputDTO(
    @field:NotBlank val nome: String,
    @field:Email val email: String,
    @field:NotBlank val senha: String,
    @field:CPF val cpf: String,
    @field:Past val dataNascimento: LocalDate,
    @field:NotBlank val endereco: EnderecoDTO,
    @field:NotBlank val contato: ContatoDTO,
    @field:NotBlank val identificador: IdentificadorDTO,
    @field:NotBlank val rendaFamiliar: String,
    @field:NotBlank val quantidadePessoas: Int,
    @field:NotBlank val urgenciaFamiliar: String,
    @field:NotBlank val pessoaDeficiencia: Boolean,
    @field:NotBlank val familia: FamiliaDTO,
    @field:NotBlank val quantidadeCriancas: Int,
    @field:NotBlank val crianca: CriancaDTO,
    @field:PastOrPresent val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @field:PastOrPresent val dataUltimaAtualizacao: LocalDateTime = LocalDateTime.now(),
    @field:Email @field:Size(max = 100) val emailModificador: String
)
