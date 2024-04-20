package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.Familia

interface FamiliaRepository:JpaRepository<Familia, Int> {
}