package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetotfg.domain.cadastro.Bairro
import school.sptech.projetotfg.domain.cadastro.Cidade
import school.sptech.projetotfg.dto.*
import school.sptech.projetotfg.service.UsuarioService

@RestController
@RequestMapping("/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("/cadastro")
    fun cadastrarUsuario(@RequestBody usuarioInputDTO: UsuarioInputDTO): ResponseEntity<UsuarioResponseDTO> {
        try {

            val usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioInputDTO)
            return ResponseEntity.status(201).body(usuarioResponseDTO)

        } catch (ex: ResponseStatusException) {

            return ResponseEntity.status(500).build()

        }
    }

    @PutMapping("/{id}")
    fun atualizarUsuario(
        @PathVariable id: Long,
        @RequestBody usuarioInputDTO: UsuarioInputDTO
    ): ResponseEntity<UsuarioResponseDTO> {
        try {
            val usuarioResponseDTO = usuarioService.atualizarUsuario(id, usuarioInputDTO)
            return ResponseEntity.status(200).body(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }

    @GetMapping
    fun listarUsuarios(): ResponseEntity<List<UsuarioResponseDTO>> {
        val usuarios = usuarioService.listarUsuarios()
        return ResponseEntity.ok(usuarios)
    }

    @DeleteMapping("/{id}")
    fun excluirUsuario(@PathVariable id: Long): ResponseEntity<Any> {
        try {
            var usuarioResponseDTO = usuarioService.encontrarUsuario(id)
            usuarioService.excluirUsuario(id)
            return ResponseEntity.status(200).body(usuarioResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).body("Usuário não encontrado")
        }
    }

    @GetMapping("/cidades")
    fun getCidades():ResponseEntity<MutableList<Cidade>>{

        val cidades = usuarioService.getCidades()
        return ResponseEntity.status(200).body(cidades)

    }

    @GetMapping("/bairro")
    fun getBairro():ResponseEntity<MutableList<Bairro>>{

        val bairros = usuarioService.getBairro()
        return ResponseEntity.status(200).body(bairros)

    }

    @PostMapping("/completo/cadastro")
    fun cadastrarUsuarioCompleto(@RequestBody usuarioCompletoInputDTO: CadastroCompletoInputDTO?): ResponseEntity<UsuarioCompletoResponseDTO> {

        val usuarioCompletoResponseDTO = usuarioService.separarArqs(usuarioCompletoInputDTO!!)
        return ResponseEntity.status(201).body(usuarioCompletoResponseDTO)

    }

    @PostMapping("/completo/cadastro/massa")
    fun cadastrarUsuarioCompletoMassa(@RequestBody usuarioCompletoInputDTO:CadastroMassaDTO): ResponseEntity<Void> {

        val resposta:Boolean = usuarioService.cadastrarUsuarioCompletoMassa(usuarioCompletoInputDTO)

        if (resposta){
            return ResponseEntity.status(200).build()
        }else{
            return ResponseEntity.status(500).build()
        }


    }

    @PutMapping("/completo/{id}")
    fun atualizarUsuarioCompleto(
        @PathVariable id: Long,
        @RequestBody usuarioCompletoInputDTO: UsuarioCompletoInputDTO
    ): ResponseEntity<UsuarioCompletoResponseDTO> {
        try {
            val usuarioCompletoResponseDTO = usuarioService.atualizarUsuarioCompleto(id, usuarioCompletoInputDTO)
            return ResponseEntity.status(200).body(usuarioCompletoResponseDTO)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }

    @GetMapping("/completo")
    fun listarUsuariosCompletos(): ResponseEntity<List<UsuarioCompletoResponseDTO>> {
        val usuarios = usuarioService.listarUsuariosCompletos()
        return ResponseEntity.ok(usuarios)
    }

    @PostMapping("/cadastro/massa/csv")
    fun cadastrarUsuarioMassaCsv(@RequestBody csv:String): ResponseEntity<Void>{

        val resposta:Boolean = usuarioService.cadastrarUsuarioMassaCsv(csv)

        if (resposta){
            return ResponseEntity.status(201).build()
        }else{
            return ResponseEntity.status(500).build()
        }

    }

}
