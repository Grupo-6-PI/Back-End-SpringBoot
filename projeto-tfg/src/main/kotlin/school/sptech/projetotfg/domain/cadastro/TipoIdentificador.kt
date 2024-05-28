package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class TipoIdentificador(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idTipoIdentificador:Long = 0,
    @field:Max(50) private var tipo:String,
    @field:ManyToOne private var situacao: Situacao
) {
    fun getId():Long{
        return idTipoIdentificador
    }
    fun setId(novoId:Long){
        idTipoIdentificador = novoId
    }
    fun getTipo():String{
        return tipo
    }
    fun setTipo(novoTipo:String){
        tipo = novoTipo
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}