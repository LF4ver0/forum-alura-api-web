package br.com.alura.forum

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@OpenAPIDefinition(
    info = Info(
        title = "Curso Backend Alura Kotlin + Spring Boot",
        version = "1.0",
        description = "Lista de APIs do Curso"
    )
)

@SpringBootApplication
@EnableCaching
class ForumApplication

fun main(args: Array<String>) {
	runApplication<ForumApplication>(*args)
}
