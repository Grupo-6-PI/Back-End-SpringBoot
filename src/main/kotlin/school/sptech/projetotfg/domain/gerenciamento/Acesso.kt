package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import school.sptech.projetotfg.domain.cadastro.Usuario
import java.time.LocalDate

@Entity
class Acesso(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = null,
    @field:NotNull private var dataAcesso: LocalDate,
    @field:NotNull @field:ManyToOne var situacao: Situacao,
    @field:NotNull @field:ManyToOne private var usuario: Usuario
){

    constructor(
        paramdataAcesso: LocalDate,
        paramsituacao: Situacao,
        paramusuario: Usuario
    ):this(
        dataAcesso = paramdataAcesso,
        situacao = paramsituacao,
        usuario = paramusuario
    )

}