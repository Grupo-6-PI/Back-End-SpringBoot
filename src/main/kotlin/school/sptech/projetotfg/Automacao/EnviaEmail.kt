package school.sptech.projetotfg.Automacao

import jakarta.mail.Authenticator
import jakarta.mail.Message
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session.*
import jakarta.mail.Transport
import jakarta.mail.internet.MimeMessage
import java.util.*

fun MandarEmail(){

    var propriedades = Properties()
    propriedades.put("mail.smtp.auth", true)
    propriedades.put("mail.smtp.starttls.enable", true)
    propriedades.put("mail.smtp.host", "smtp.gmail.com")
    propriedades.put("mail.smtp.port", "587")

    var secao = getDefaultInstance(propriedades, object:Authenticator(){
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication("paulocafasso@gmail.com","")
        }
    })

    //MimeMessage object é uma mensagem de email com formatação MIME (Multipurpose Internet Mail Extension)
    var mensagem:MimeMessage = MimeMessage(secao)
    //mensagem.setFrom("contato@moocasolidaria.com.br")
    mensagem.setFrom("paulocafasso@gmail.com")
    mensagem.setRecipients(Message.RecipientType.TO,"paulo.cafasso@sptech.school")
    mensagem.setSubject("Teste de envio","utf 8")
    mensagem.setText("""
        Boa noite, se esse e-mail chegar, deu certo!
    """.trimIndent())
    Transport.send(mensagem)
}

fun main() {
    MandarEmail()
}
















// configuração do host da mooca solidaria com a linha de comandos cmd :
// Open up a command prompt (CMD.exe) Type nslookup and hit enter Type set type=MX and hit enter Type the domain name and hit enter, for example: google.com The results will be a list of host names that are set up for SMTP
// host mooca solidaria ==








//var secao:Session = Session.getInstance(propriedades, Authenticator(){
//    @Override
//    fun PasswordAuthentication (){

//    }
//})