package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Endereco
import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais

interface EnderecoRepository : JpaRepository<Endereco, Long> {
}