package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class Bairro(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idBairro:Int,
    @field:NotBlank @field:Max(100) var nome:String,
    @ManyToOne var cidade:Cidade
) {
}