package school.sptech.projetotfg.dto

import jakarta.validation.constraints.Max

class CidadeDTO(
    @field:Max(100) private var nome:String
) {
}