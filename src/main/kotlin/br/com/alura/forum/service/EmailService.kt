package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar() {
        val message = SimpleMailMessage().apply {
            subject = "Assunto do email"
            text = "Corpo da mensagem"
            setTo("destinatario@exemplo.com")
            from = "seuemail@exemplo.com"
        }
        javaMailSender.send(message)
    }

}