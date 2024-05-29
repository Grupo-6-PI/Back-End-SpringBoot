package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.atividades.Atividade

interface AtividadeRepository:JpaRepository<Atividade, Int> {
}