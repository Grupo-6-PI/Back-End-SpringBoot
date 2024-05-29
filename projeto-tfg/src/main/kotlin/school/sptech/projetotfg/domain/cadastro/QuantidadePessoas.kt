package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idQuantidadePessoas:Long = 0,
    @field:NotNull private var minimo: Long,
    @field:NotNull private var maximo: Long,
    @field:ManyToOne private var situacao: Situacao
    ) {
    fun getId():Long{
        return idQuantidadePessoas
    }
    fun setId(novoId:Long){
        idQuantidadePessoas = novoId
    }
    fun getMinimo():Int{
        return minimo
    }
    fun setMinimo(novoMinimo:Int){
        minimo = novoMinimo
    }
    fun getMaximo():Int{
        return maximo
    }
    fun setMaximo(novoMaximo:Int){
        maximo = novoMaximo
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}