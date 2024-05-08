package school.sptech.projetotfg.service

import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import school.sptech.projetotfg.domain.Usuario
import school.sptech.projetotfg.repository.UsuarioRepository

@Service
class UsuarioService(
    val usuarioRepository: UsuarioRepository,
    val mapper: ModelMapper = ModelMapper()
) {

    fun cadastrarUsuario(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        if(!usuarioRepository.existsById(usuario.idUsuario)){
            usuarioRepository.save(usuario)
            return ResponseEntity.status(201).body(usuario)
        }
        return ResponseEntity.status(400).build()
    }
}