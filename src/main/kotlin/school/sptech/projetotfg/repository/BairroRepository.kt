package school.sptech.projetotfg.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.cadastro.Endereco
import school.sptech.projetotfg.domain.cadastro.InformacoesAdicionais
import java.util.Optional

interface BairroRepository : JpaRepository<Bairro, Long> {

    fun findByNomeAndCidadeId(nome:String, cidade_id:Long):Optional<Bairro>

}