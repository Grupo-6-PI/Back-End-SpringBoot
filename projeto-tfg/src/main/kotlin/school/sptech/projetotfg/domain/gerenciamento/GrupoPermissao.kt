package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long = 0,
    @field:ManyToOne private var permissaoId: Permissao,
    @field:ManyToOne private var nivelAcessoId: NivelAcesso
){
}