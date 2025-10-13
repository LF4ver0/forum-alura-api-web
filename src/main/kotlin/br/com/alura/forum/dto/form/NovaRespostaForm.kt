package br.com.alura.forum.dto.form

import jakarta.validation.constraints.NotEmpty

data class NovaRespostaForm(
    @field:NotEmpty
    val mensagem: String,
    val idAutor: Long
)