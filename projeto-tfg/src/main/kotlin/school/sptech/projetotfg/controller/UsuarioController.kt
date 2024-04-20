package com.example.v1backtfg

import com.example.projeto.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/usuario")
class UsuarioController {
    val listaUsuarios: MutableList<Usuario> = mutableListOf()

    @GetMapping("/buscar_usuario/{id}")
    fun PegaUsuarioPorId(@PathVariable id:Int):ResponseEntity<Usuario>{
        if(temUsuario(id)){
            return ResponseEntity.status(200).body(listaUsuarios[id])
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun cadastrarUsuario(@RequestBody usuario:Usuario):ResponseEntity<String>{
        if(usuario.verificacaoCadastro(usuario)){
            if(!temUsuario(usuario.id_usuario)){
                listaUsuarios.add(usuario)
                return ResponseEntity.status(201).body("Usuário ${usuario.nome} cadastro com sucesso!")
            }
        }
        return ResponseEntity.status(400).build()
    }

    @PutMapping("/{id}")
    fun atualizarUsuarioPorId(
        @PathVariable id:Int,
        @RequestBody usuario:Usuario
        ):ResponseEntity<Usuario>{
            if (temUsuario(id)){
                listaUsuarios.set(id,usuario)
                return ResponseEntity.status(200).body(listaUsuarios[id])
            }
                return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun removerUsuarioPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(temUsuario(id)){
            listaUsuarios.removeAt(id)
            return ResponseEntity.status(200).body("Usuario deletado com sucesso!")
        }
            return ResponseEntity.status(404).build()
    }

    fun temUsuario(id:Int):Boolean{
        return id > 0 && id < listaUsuarios.size //valida se id já existe
    }
}