package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import school.sptech.projetotfg.domain.gerenciamento.Situacao

@Entity
class UrgenciaFamiliar(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idUrgenciaFamiliar:Long = 0,
    @field:Max(150) private var descricao:String,
    @field:ManyToOne private var situacao: Situacao
) {
    fun getId():Long{
        return idUrgenciaFamiliar
    }
    fun setId(novoId:Long){
        idUrgenciaFamiliar = novoId
    }
    fun getDescricao():String{
        return descricao
    }
    fun setDescricao(novaDescricao:String){
        descricao = novaDescricao
    }
    fun getSituacao(): Situacao {
        return situacao
    }
    fun setSituacao(novaSitucao: Situacao){
        situacao = novaSitucao
    }
}