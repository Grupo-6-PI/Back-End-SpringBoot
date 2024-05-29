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
    fun getMinimo():Long{
        return minimo
    }
    fun setMinimo(novoMinimo:Long){
        minimo = novoMinimo
    }
    fun getMaximo():Long{
        return maximo
    }
    fun setMaximo(novoMaximo:Long){
        maximo = novoMaximo
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}