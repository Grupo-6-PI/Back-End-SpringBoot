package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*
import school.sptech.projetotfg.domain.gerenciamento.Situacao


@Entity
class Endereco(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    @field:Max(100) private var logradouro:String? = null,
    @field:Positive private var numero: Long? = null,
    @field:Size(min = 8, max = 8) private var cep:String? = null,
    @field:ManyToOne private var bairro: Bairro? = null,
    @field:ManyToOne private var situacao: Situacao? = null
){
}