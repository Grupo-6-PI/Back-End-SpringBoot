package school.sptech.projetotfg.complement

import org.modelmapper.ModelMapper
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatusCode
import org.springframework.web.server.ResponseStatusException

abstract class Service{

    fun validarLista(lista: MutableList<*>) {

        if (lista.isEmpty()){

            throw ResponseStatusException(HttpStatusCode.valueOf(204))

        }

    }

    fun validarId(id: Long, repository: JpaRepository<*,Long>) {

        if(!repository.existsById(id)){
            throw ResponseStatusException(HttpStatusCode.valueOf(404))
        }

    }

}