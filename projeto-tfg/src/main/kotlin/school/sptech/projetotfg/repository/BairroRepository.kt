package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.cadastro.Cidade

interface BairroRepository : JpaRepository<Bairro, Int> {
    fun findByNameAndCidade(nome: String, cidade: Cidade): Bairro?
}