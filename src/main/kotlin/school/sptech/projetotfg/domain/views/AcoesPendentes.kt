package school.sptech.projetotfg.domain.views

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class AcoesPendentes(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var nome:String?,
    private var dia_numeracao:Int?,
    private var mes_numeracao:Int?,
    private var ano:Int?,
) {

    fun getId():Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getNome():String?{
        return nome
    }

    fun setNome(new:String?){
        this.nome = new
    }

    fun getDiaNumeracao():Int?{
        return dia_numeracao
    }

    fun setDiaNumeracao(new:Int?){
        this.dia_numeracao = new
    }

    fun getMesNumeracao():Int?{
        return mes_numeracao
    }

    fun setMesNumeracao(new:Int?){
        this.mes_numeracao = new
    }

    fun getano():Int?{
        return ano
    }

    fun setano(new:Int?){
        this.ano = new
    }

}