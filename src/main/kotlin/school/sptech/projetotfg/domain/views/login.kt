package school.sptech.projetotfg.domain.views

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class login(
    @field:Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id:Long? = null,
    private var nome:String? = null,
    private var email:String? = null,
    private var senha:String? = null,
) {

}