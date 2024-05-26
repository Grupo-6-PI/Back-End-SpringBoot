package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.TipoIdentificador

interface TipoIdentificadorRepository : JpaRepository<TipoIdentificador, Int> {
    fun findByTipo(tipo: String): TipoIdentificador?
}