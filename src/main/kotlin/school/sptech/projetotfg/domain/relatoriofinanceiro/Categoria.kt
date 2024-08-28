package school.sptech.projetotfg.domain.relatoriofinanceiro

import jakarta.persistence.*
import jakarta.validation.constraints.Max

@Entity
class Categoria(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long?,
    @field:Max(60) private var nome:String?
) {

    @OneToMany(mappedBy = "categoria")
    var vendas:MutableList<Venda> = mutableListOf()

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

}