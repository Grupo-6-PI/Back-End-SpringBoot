package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class RendaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idRendaFamiliar:Int = 0,
    @field:Min(0) private var renda:Double,
    @ManyToOne private var situacao: Situacao
) {
    fun getId():Int{
        return idRendaFamiliar
    }
    fun setId(novoId:Int){
        idRendaFamiliar = novoId
    }
    fun getRenda():Double{
        return renda
    }
    fun setRenda(novaRenda:Double){
        renda = novaRenda
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSituacao: Situacao){
        situacao = novaSituacao
    }
}