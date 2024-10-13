package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.TamanhoCalcado

interface TamanhoCalcadoRepository :JpaRepository<TamanhoCalcado,Long> {

}
