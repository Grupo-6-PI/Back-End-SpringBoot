package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*


@Entity
class Endereco (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idEndereco:Int,
    @field:Max(100) var logradouro:String,
    @field:Positive var numero: Int,
    @field:Size(min = 8, max = 8) var cep:String, //tamanho 8 supõe que não será o traço"-" armazenado
    @ManyToOne var bairro: Bairro,
    @ManyToOne var situacao: Situacao
){
}