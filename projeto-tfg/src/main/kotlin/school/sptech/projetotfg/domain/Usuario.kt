package com.example.projeto

import java.time.LocalDate

-tfg

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Usuario(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int?,
    @field:NotBlank  var nome:String,
    @field:NotBlank var sobrenome:String,
    @field:NotNull @field:Past var dtNascimento:LocalDate,
    @field:NotBlank val nivelAcesso:Int
) {

    fun verificacaoCadastro(usuario:Usuario):Boolean{
        if(usuario.nome=="" && usuario.sobrenome==""){
            return true
        }
            return false
    }

}