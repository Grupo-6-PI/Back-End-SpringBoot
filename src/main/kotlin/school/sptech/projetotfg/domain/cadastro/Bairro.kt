package school.sptech.projetotfg.domain.cadastro

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
class Bairro(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    private var nome:String?,
    @field:ManyToOne private var cidade: Cidade?
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

    fun getCidade(): Cidade? {
        return cidade
    }

    fun setCidade(new: Cidade?) {
        this.cidade = new
    }

}