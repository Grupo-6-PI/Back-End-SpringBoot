package school.sptech.projetotfg.repository

import com.example.projeto.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository:JpaRepository<Usuario, Int> {

}