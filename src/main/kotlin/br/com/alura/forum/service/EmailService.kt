package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(emailAutor: String) {
        val message = SimpleMailMessage().apply {
            subject = "[Alura] Resposta Recebida"
            text = "Olá, o seu tópico foi respondido. Vamos lá conferir ?"
            setTo(emailAutor)
            from = "naoresponda@alura.com"
        }
        javaMailSender.send(message)
    }

}