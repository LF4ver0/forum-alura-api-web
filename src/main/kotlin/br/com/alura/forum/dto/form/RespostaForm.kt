package br.com.alura.forum.dto.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class RespostaForm(
    @field:NotEmpty
    @field:Size(min = 5, max = 500)
    val mensagem: String,

    val idAutor: Long,
    val idTopico: Long,
    val solucao: Boolean
)