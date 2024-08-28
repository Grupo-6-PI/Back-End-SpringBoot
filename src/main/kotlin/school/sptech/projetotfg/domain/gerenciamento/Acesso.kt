package school.sptech.projetotfg.domain.gerenciamento

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import school.sptech.projetotfg.domain.cadastro.Usuario
import java.time.LocalDate

@Entity
class Acesso(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = null,
    @field:NotNull private var dataAcesso: LocalDate?,
    @field:NotNull @field:ManyToOne private var situacao: Situacao?,
    @field:NotNull @field:ManyToOne private var usuario: Usuario?
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

    fun getId(): Long?{
        return id
    }

    fun setId(new: Long?){
        this.id = new
    }

    fun getDataAcesso(): LocalDate?{
        return dataAcesso
    }

    fun setDataAcesso(new: LocalDate?){
        this.dataAcesso = new
    }

    fun getSituacao(): Situacao?{
        return situacao
    }

    fun setSituacao(new: Situacao?){
        this.situacao = new
    }

    fun getUsuario(): Usuario?{
        return usuario
    }

    fun setUsuario(new: Usuario?){
        this.usuario = new
    }

}