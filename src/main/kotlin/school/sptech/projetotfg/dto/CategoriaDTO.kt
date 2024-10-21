package school.sptech.projetotfg.dto

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max

class CategoriaDTO(
    var id:Long?,
    var nome:String?
) {
}