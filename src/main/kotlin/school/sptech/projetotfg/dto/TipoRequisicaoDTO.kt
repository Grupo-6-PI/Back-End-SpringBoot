package school.sptech.projetotfg.dto

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

class TipoRequisicaoDTO(
    var id: Long?,
    var assunto:String?
) {
}