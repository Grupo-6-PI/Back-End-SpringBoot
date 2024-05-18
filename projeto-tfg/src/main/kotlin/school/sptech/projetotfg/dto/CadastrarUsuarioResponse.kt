package school.sptech.projetotfg.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CadastrarUsuarioResponse(
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String
) {

}