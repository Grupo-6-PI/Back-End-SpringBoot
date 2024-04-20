package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import org.jetbrains.annotations.NotNull

@Entity
class InformacoesContato (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idInformacoesContato:Int,
    @field:Max(150) @field:NotNull var contato:String,
    @ManyToOne var tipoContato:TipoContato,
    @ManyToOne var logContato:Contato, //a variavel contato da comflito com a varivel na linha 10
    @ManyToOne var situacao: Situacao,

){
}