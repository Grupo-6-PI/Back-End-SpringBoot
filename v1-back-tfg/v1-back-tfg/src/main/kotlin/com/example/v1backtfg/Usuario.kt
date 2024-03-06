package com.example.v1backtfg

import java.time.LocalDateTime
import java.util.Date

class Usuario (
    val id:Int,
    val nome:String,
    val sobrenome:String,
    val dtNascimento:String,
    val nivelAcesso:Int
) {

    fun verificacaoCadastro(usuario:Usuario):Boolean{
        if(usuario.nome=="" && usuario.sobrenome==""){
            return true
        }
            return false
    }

}