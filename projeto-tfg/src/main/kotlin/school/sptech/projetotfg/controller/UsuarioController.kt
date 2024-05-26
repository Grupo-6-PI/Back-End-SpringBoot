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
        @PostMapping
        fun cadastrarBeneficiario(@Validated @RequestBody dto: BeneficiarioInputDTO): ResponseEntity<BeneficiarioResponseDTO> {
            return try {
                val responseDTO = beneficiarioService.cadastrarBeneficiario(dto)
                ResponseEntity(responseDTO, HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
            }
        }
    }


