package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var usuarios: List<Usuario>){
    init{
        val autor = Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "email@email.com.br"
        )
        usuarios = Arrays.asList(autor)
    }
    fun buscaPorId(id: Long) : Usuario? {
        return usuarios.find { it.id == id }
    }
}