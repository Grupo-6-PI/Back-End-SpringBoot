package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.Usuario
import school.sptech.projetotfg.dto.cadastrarUsuarioResponse
import school.sptech.projetotfg.repository.AcessoRepository
import school.sptech.projetotfg.repository.UsuarioRepository
import org.modelmapper.ModelMapper
import school.sptech.projetotfg.domain.Acesso
import school.sptech.projetotfg.dto.LoginAcessoResponse

@RestController
@RequestMapping("/acesso")
class AcessoController(
    private val AcessoRepository: AcessoRepository,
    private val UsuarioRepository: UsuarioRepository,
    val mapper: ModelMapper= ModelMapper()
) {

    var status: Boolean = false

    @PostMapping("/login")
    fun login(@RequestBody usuario: LoginAcessoResponse): ResponseEntity<String> {
        var usuarioLogado: Usuario = mapper.map(usuario, Usuario::class.java)

        return ResponseEntity.status(201).body("Login feito")
        status = true
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String>  {
        if (status) {
            AcessoRepository.logout()
            ResponseEntity.ok("Logout successful")
        } else {
             ResponseEntity.status(405).body("Logout failed: Usuário não realizou logIn")
        }
         return ResponseEntity.status(201).body("Logout feito")
        status = false
    }

}
