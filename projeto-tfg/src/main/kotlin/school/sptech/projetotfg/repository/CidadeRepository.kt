package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Cidade

interface CidadeRepository : JpaRepository<Cidade, Int> {
    fun findByNome(nome: String): Cidade?
}