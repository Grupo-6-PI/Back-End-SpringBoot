package school.sptech.projetotfg.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.cadastro.Usuario
import school.sptech.projetotfg.dto.BeneficiarioInputDTO
import school.sptech.projetotfg.dto.BeneficiarioResponseDTO
import school.sptech.projetotfg.service.BeneficiarioService

@RestController
@RequestMapping("/beneficiarios")
class UsuarioController(
    private val beneficiarioService: BeneficiarioService
){
    @PostMapping("/cadastrar")
    fun cadastrarBeneficiario(dto: BeneficiarioInputDTO): ResponseEntity<BeneficiarioResponseDTO> {
        val cadastro = beneficiarioService.cadastrarBeneficiario(dto)
        return ResponseEntity.status(200).body(cadastro)
 }
    @GetMapping("/obterUsuario")
    fun obterBeneficiarioPorId(id: Long): ResponseEntity<Usuario> {
        val existeID = beneficiarioService.obterBeneficiarioPorId(id)
        return ResponseEntity.status(200).body(existeID)
    }

    @PostMapping("/atualiazarCadastro")
    fun atualizarBeneficiario(id: Long, novoDTO: BeneficiarioInputDTO): ResponseEntity<BeneficiarioResponseDTO> {
        val attCadastro = beneficiarioService.atualizarBeneficiario(id, novoDTO)
        return ResponseEntity.status(200).body(attCadastro)
    }

    @DeleteMapping("/excluirUsuario")
    fun excluirBeneficiario(id: Long): ResponseEntity<Boolean> {
        val deletCadastro = beneficiarioService.excluirBeneficiario(id)
        return ResponseEntity.status(200).body(deletCadastro)
    }

    }


