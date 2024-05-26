package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.gerenciamento.NivelAcesso

interface NivelAcessoRepository : JpaRepository<NivelAcesso, Int> {
    fun findByApelido(apelido: String): NivelAcesso?
}