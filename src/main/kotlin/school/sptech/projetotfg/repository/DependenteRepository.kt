package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Dependente
import kotlin.jvm.internal.Ref.LongRef

interface DependenteRepository :JpaRepository<Dependente, Long> {
}