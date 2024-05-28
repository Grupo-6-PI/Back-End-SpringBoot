package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.dto.BeneficiarioInputDTO
import school.sptech.projetotfg.dto.BeneficiarioResponseDTO
import school.sptech.projetotfg.service.BeneficiarioService

@RestController
@RequestMapping("/beneficiarios")
class UsuarioController(
    private val beneficiarioService: BeneficiarioService
){

    }


