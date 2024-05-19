package school.sptech.projetotfg.controller

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.Usuario
import school.sptech.projetotfg.repository.UsuarioRepository


@RestController
@RequestMapping("/usuario")
class UsuarioController(private val repository: UsuarioRepository, val mapper: ModelMapper= ModelMapper()) {


    @GetMapping("/listar_usuarios")
    fun listaUsuariosCadastrados():ResponseEntity<List<Usuario>>{
        val listaUsuarios = repository.findAll()
        if(listaUsuarios.isNotEmpty()){
            return ResponseEntity.status(200).body(listaUsuarios)
        }
            return ResponseEntity.status(204).build()
    }

    @GetMapping("/buscar_usuario/{id}")
    fun pegaUsuarioPorId(@PathVariable id:Int):ResponseEntity<Usuario>{
        if(repository.existsById(id)){
            val usuario = repository.findById(id).get()
            return ResponseEntity.status(200).body(usuario)
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun cadastrarUsuario(@RequestBody usuario:cadastrarUsuarioResponse):ResponseEntity<Usuario>{

        var usuarioNovo:Usuario = mapper.map(usuario,Usuario::class.java)

        return ResponseEntity.status(201).body(usuarioNovo)
    }

    @PutMapping("/alterar_dados")
    fun atualizaUsuarioPorId(@RequestBody usuarioAtualizado:Usuario):ResponseEntity<Usuario>{
            if (repository.existsById(usuarioAtualizado.idUsuario)){
                val usuario = repository.findById(usuarioAtualizado.idUsuario).get()
                usuario.nome = usuarioAtualizado.nome
                usuario.email = usuarioAtualizado.email
                usuario.informacoesAdicionais = usuarioAtualizado.informacoesAdicionais
                usuario.situacao = usuarioAtualizado.situacao
                repository.save(usuario)
                return ResponseEntity.status(200).body(usuario)
            }
                return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun removeUsuarioPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(repository.existsById(id)){
            repository.deleteById(id)
            return ResponseEntity.status(200).body("Usuario removido com sucesso!")
        }
            return ResponseEntity.status(404).build()
    }

}