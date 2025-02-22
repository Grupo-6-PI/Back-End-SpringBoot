package school.sptech.projetotfg.domain.relatoriofinanceiro

import jakarta.persistence.*
import school.sptech.projetotfg.domain.atividades.Calendario

@Entity
class Venda(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long?,
    private var quantidade: Int?,
    internal var valor: Double?,
    private var emailModificador: String?,
    @field:ManyToOne
    private var categoria: Categoria?,
    @field:ManyToOne
    private var calendario: Calendario?
) {

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getQuantidade(): Int? {
        return quantidade
    }

    fun setQuantidade(quantidade: Int?) {
        this.quantidade = quantidade
    }

    fun getValor(): Double? {
        return valor
    }

    fun setValor(valor: Double?) {
        this.valor = valor
    }

    fun getEmailModificador(): String? {
        return emailModificador
    }

    fun setEmailModificador(emailModificador: String?) {
        this.emailModificador = emailModificador
    }

    fun getCategoria(): Categoria? {
        return categoria
    }

    fun setCategoria(categoria: Categoria?) {
        this.categoria = categoria
    }

    fun getCalendario(): Calendario? {
        return calendario
    }

    fun setCalendario(calendario: Calendario?) {
        this.calendario = calendario
    }

}