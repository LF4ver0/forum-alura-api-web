package br.com.alura.forum.model

object UsuarioTest {
    fun build() = Usuario(
        nome = "Teste",
        email = "teste@email.com",
        password = "123456"
    )
}