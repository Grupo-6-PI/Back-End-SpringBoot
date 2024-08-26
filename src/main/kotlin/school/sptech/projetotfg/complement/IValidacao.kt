package school.sptech.projetotfg.complement

import org.springframework.data.jpa.repository.JpaRepository

interface IValidacao {

    fun validarLista(lista: MutableList<*>)

    fun validarId(id:Long,repository: JpaRepository<*,Long>)

}