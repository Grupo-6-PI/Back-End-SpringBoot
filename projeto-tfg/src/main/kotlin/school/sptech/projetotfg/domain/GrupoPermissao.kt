package school.sptech.projetotfg.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class GrupoPermissao (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) var idGrupoPermissao:Int,
    @ManyToOne var Permissao:Permissao,
    @ManyToOne var NivelAcesso:NivelAcesso
){
}