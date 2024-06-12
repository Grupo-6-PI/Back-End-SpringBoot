package school.sptech.projetotfg.domain.views

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class AcoesPendentes(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long,
    private var nome:String,
    private var dia_numeracao:Int,
    private var mes_numeracao:Int,
    private var ano:Int,
) {

}