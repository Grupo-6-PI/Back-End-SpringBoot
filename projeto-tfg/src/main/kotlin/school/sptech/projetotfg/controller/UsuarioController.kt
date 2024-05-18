package school.sptech.projetotfg.controller

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.Usuario
import school.sptech.projetotfg.repository.UsuarioRepository
import school.sptech.projetotfg.dto.CadastrarUsuarioResponse;


@RestController
@RequestMapping("/usuario")
class UsuarioController(private val repository: UsuarioRepository, val mapper: ModelMapper= ModelMapper()) {


    @GetMapping("/listar_usuarios")
    fun getListaUsuariosCadastrados():ResponseEntity<List<Usuario>>{
        val listaUsuariosCadastrados = repository.findAll()
        if(listaUsuariosCadastrados.isNotEmpty()){
            return ResponseEntity.status(200).body(listaUsuariosCadastrados)
        }
            return ResponseEntity.status(204).build()
    }

    @GetMapping("/buscar_usuario/{id}")
    fun getUsuarioPorId(@PathVariable id:Int):ResponseEntity<Usuario>{
        if(repository.existsById(id)){
            val usuarioEncontrado = repository.findById(id).get()
            return ResponseEntity.status(200).body(usuarioEncontrado)
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun postUsuario(@RequestBody usuario:CadastrarUsuarioResponse):ResponseEntity<Usuario>{

        var usuarioNovo:Usuario = mapper.map(usuario,Usuario::class.java)

        return ResponseEntity.status(201).body(usuarioNovo)
    }

    @PutMapping("/alterar_dados")
    fun putUsuarioPorId(@RequestBody dadosNovosDoUsuario:Usuario):ResponseEntity<Usuario>{
            if (repository.existsById(dadosNovosDoUsuario.idUsuario)){
                val usuario:Usuario = repository.findById(dadosNovosDoUsuario.idUsuario).get()
                usuario.setNome(dadosNovosDoUsuario.getNome())
                usuario.email = dadosNovosDoUsuario.email
                usuario.informacoesAdicionais = dadosNovosDoUsuario.informacoesAdicionais
                usuario.situacao = dadosNovosDoUsuario.situacao
                repository.save(usuario)
                return ResponseEntity.status(200).body(usuario)
            }
                return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun deleteUsuarioPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(repository.existsById(id)){
            repository.deleteById(id)
            return ResponseEntity.status(200).body("Usuario removido com sucesso!")
        }
            return ResponseEntity.status(404).build()
    }

}