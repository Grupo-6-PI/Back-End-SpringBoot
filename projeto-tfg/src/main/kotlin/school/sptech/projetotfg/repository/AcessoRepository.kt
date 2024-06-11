package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.domain.gerenciamento.Acesso

interface AcessoRepository : JpaRepository<Acesso, Long> {
        fun findTopByUsuarioOrderByIdDesc(usuario: Usuario): Acesso?

        fun findTopByUsuarioOrderByDataAcessoDesc(usuario: Usuario?): Acesso?
}
