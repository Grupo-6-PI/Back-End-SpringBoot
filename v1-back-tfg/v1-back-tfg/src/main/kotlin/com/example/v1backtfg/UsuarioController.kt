package com.example.v1backtfg

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
class UsuarioController {
    val listaUsuarios: MutableList<Usuario> = mutableListOf()
    //val listaUsuarios = mutableListOf<Usuario>()

    @GetMapping("/buscar_usuario/{id}")
    fun PegaUsuarioPorId(@PathVariable id:Int):Usuario?{
        if(temUsuario(id)){
        return listaUsuarios[id]
        }
        return null
    }

    @PostMapping
    fun cadastrarUsuario(@RequestBody usuario:Usuario):Boolean{
        if(temUsuario(usuario.id)){
            return false
        }

        listaUsuarios.add(usuario)

        return true
    }

    @PutMapping("/{id}")
    fun atualizarUsuarioPorId(
        @PathVariable id:Int,
        @RequestBody usuario:Usuario
        ):Usuario?{
            if (temUsuario(id)){
                listaUsuarios.set(id,usuario)
                return listaUsuarios[id]
            }
                return null
    }

    @DeleteMapping("/{id}")
    fun removerUsuarioPorId(@PathVariable id:Int):Boolean{
        if(temUsuario(id)){
            listaUsuarios.removeAt(id)
            return true
        }
            return false
    }

    fun temUsuario(id:Int):Boolean{
        return id >= 0 && id < listaUsuarios.size
    }
}