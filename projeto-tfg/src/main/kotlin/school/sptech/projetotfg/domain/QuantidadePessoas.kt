package school.sptech.projetotfg.domain

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
class QuantidadePessoas(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idQuantidadePessoas:Int,
    @field:NotNull private var minimo:Int,
    @field:NotNull private var maximo:Int,
    @ManyToOne private var situacao: Situacao
    ) {
    fun getId():Int{
        return idQuantidadePessoas
    }
    fun setId(novoId:Int){
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
    fun getSituacao():Situacao{
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}