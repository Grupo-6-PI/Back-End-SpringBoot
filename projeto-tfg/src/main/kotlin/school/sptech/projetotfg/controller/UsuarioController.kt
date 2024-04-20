package com.example.v1backtfg

import com.example.projeto.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.repository.UsuarioRepository


@RestController
@RequestMapping("/usuario")
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    @GetMapping("/listar_usuarios")
    fun listaUsuariosCadastrados():ResponseEntity<List<Usuario>>{
        val listaUsuarios = usuarioRepository.findAll()
        if(listaUsuarios.isNotEmpty()){
            return ResponseEntity.status(200).body(listaUsuarios)
        }
            return ResponseEntity.status(204).build()
    }

    @GetMapping("/buscar_usuario/{id}")
    fun pegaUsuarioPorId(@PathVariable id:Int):ResponseEntity<Usuario>{
        if(usuarioRepository.existsById(id)){
            val usuario = usuarioRepository.findById(id).get()
            return ResponseEntity.status(200).body(usuario)
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun cadastrarUsuario(@RequestBody usuario:Usuario):ResponseEntity<Usuario>{
            if(!usuarioRepository.existsById(usuario.idUsuario)){
                usuarioRepository.save(usuario)
                return ResponseEntity.status(201).body(usuario)
            }
                return ResponseEntity.status(400).build()
    }

    @PutMapping("/alterar_dados")
    fun atualizaUsuarioPorId(@RequestBody usuarioAtualizado:Usuario):ResponseEntity<Usuario>{
            if (usuarioRepository.existsById(usuarioAtualizado.idUsuario)){
                val usuario = usuarioRepository.findById(usuarioAtualizado.idUsuario).get()
                usuario.nome = usuarioAtualizado.nome
                usuario.email = usuarioAtualizado.email
                usuario.informacoesAdicionais = usuarioAtualizado.informacoesAdicionais
                usuario.situacao = usuarioAtualizado.situacao
                usuarioRepository.save(usuario)
                return ResponseEntity.status(200).body(usuario)
            }
                return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun removeUsuarioPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id)
            return ResponseEntity.status(200).body("Usuario removido com sucesso!")
        }
            return ResponseEntity.status(404).build()
    }
}