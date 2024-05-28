package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Calendario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idCalendario: Long = 0,
    @field:ManyToOne private var dia: Dia,
    @field:ManyToOne private var ano: Ano,
    @field:ManyToOne private var mes: Mes,
    @field:ManyToOne private var diaSemana: DiaSemana
) {
    fun getId():Long{
        return idCalendario
    }
    fun setId(novoId:Long){
        idCalendario = novoId
    }
    fun getDia():Dia{
        return dia
    }
    fun setDia(novoDia:Dia){
        dia = novoDia
    }
    fun getDiaSemana():DiaSemana{
        return diaSemana
    }
    fun setDiaSemana(novoDiaSemana: DiaSemana){
        diaSemana = novoDiaSemana
    }
    fun getMes():Mes{
        return mes
    }
    fun setMes(novoMes:Mes){
        mes = novoMes
    }
    fun getAno():Ano{
        return ano
    }
    fun setAno(novoAno:Ano){
        ano = novoAno
    }
}