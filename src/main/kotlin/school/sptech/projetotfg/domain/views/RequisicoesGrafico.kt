package school.sptech.projetotfg.domain.views

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.Immutable

@Entity
@Immutable
class RequisicoesGrafico(
    @field:Id
    val id: Long?,

    val cestas_T1_pedidas: Int,
    val cestas_T1_cumpridas: Int,
    val cestas_T2_pedidas: Int,
    val cestas_T2_cumpridas: Int,
    val cestas_T3_pedidas: Int,
    val cestas_T3_cumpridas: Int,
    val cestas_T4_pedidas: Int,
    val cestas_T4_cumpridas: Int,

    val vestuario_T1_pedidas: Int,
    val vestuario_T1_cumpridas: Int,
    val vestuario_T2_pedidas: Int,
    val vestuario_T2_cumpridas: Int,
    val vestuario_T3_pedidas: Int,
    val vestuario_T3_cumpridas: Int,
    val vestuario_T4_pedidas: Int,
    val vestuario_T4_cumpridas: Int,

    val saude_T1_pedidas: Int,
    val saude_T1_cumpridas: Int,
    val saude_T2_pedidas: Int,
    val saude_T2_cumpridas: Int,
    val saude_T3_pedidas: Int,
    val saude_T3_cumpridas: Int,
    val saude_T4_pedidas: Int,
    val saude_T4_cumpridas: Int,

    val outros_T1_pedidas: Int,
    val outros_T1_cumpridas: Int,
    val outros_T2_pedidas: Int,
    val outros_T2_cumpridas: Int,
    val outros_T3_pedidas: Int,
    val outros_T3_cumpridas: Int,
    val outros_T4_pedidas: Int,
    val outros_T4_cumpridas: Int,
)