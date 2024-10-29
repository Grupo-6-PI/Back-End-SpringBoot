package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.cadastro.Cidade
import school.sptech.projetotfg.domain.cadastro.Endereco
import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais
import java.util.*

interface CidadeRepository : JpaRepository<Cidade, Long> {

    fun findByNome(nome:String): Optional<Cidade>

}