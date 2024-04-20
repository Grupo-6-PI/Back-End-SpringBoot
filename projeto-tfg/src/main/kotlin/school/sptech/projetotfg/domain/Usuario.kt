package com.example.projeto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import java.time.LocalDate

//-tfg

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.Date

@Entity
class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id_usuario: Int,
    @field:NotBlank var nome:String,
    @field:Email var email:String,
    @field:NotBlank var senha:String,
    //@field:NotNull @field:Past var data_nascimento:LocalDate,
    @field:NotBlank val nivelAcesso:Int
    // var fk_informacoes_adicionais
    // var fk_situacao
    // var fk_nivel_acesso
) {

    fun verificacaoCadastro(usuario:Usuario):Boolean{
        if(usuario.nome==""){ //valida se user entra vazio((adicionar validações de user
            return true
        }
            return false
    }

}