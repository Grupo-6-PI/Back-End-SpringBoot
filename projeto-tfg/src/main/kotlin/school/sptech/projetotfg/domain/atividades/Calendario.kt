package school.sptech.projetotfg.domain.atividades
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Calendario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var idCalendario: Int = 0,
    @ManyToOne private var dia: Dia,
    @ManyToOne private var ano: Ano,
    @ManyToOne private var mes: Mes,
    @ManyToOne private var diaSemana: DiaSemana
) {
    fun getId():Int{
        return idCalendario
    }
    fun setId(novoId:Int){
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