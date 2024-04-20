package school.sptech.projetotfg.domain

import jakarta.persistence.*
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idGrupoPermissao:Int,
    @ManyToOne var Permissao:Permissao,
    @ManyToOne var NivelAcesso:NivelAcesso
){
}