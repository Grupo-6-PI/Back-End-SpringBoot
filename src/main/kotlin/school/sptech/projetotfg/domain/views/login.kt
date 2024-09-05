package school.sptech.projetotfg.domain.views

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class login(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var nome:String? = null,
    private var email:String? = null,
    private var senha:String? = null,
) {

    fun getId(): Long? {
        return id
    }

    fun setId(new: Long?) {
        this.id = new
    }

    fun getNome(): String? {
        return nome
    }

    fun setNome(new: String?) {
        this.nome = new
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(new: String?) {
        this.email = new
    }

    fun getSenha(): String? {
        return senha
    }

    fun setSenha(new: String?) {
        this.senha = new
    }

}