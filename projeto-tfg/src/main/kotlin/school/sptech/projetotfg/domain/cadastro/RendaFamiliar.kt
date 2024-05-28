package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class RendaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idRendaFamiliar:Long = 0,
    @field:Min(0) private var renda:Double,
    @field:ManyToOne private var situacao: Situacao
) {
    fun getId():Long{
        return idRendaFamiliar
    }
    fun setId(novoId:Long){
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