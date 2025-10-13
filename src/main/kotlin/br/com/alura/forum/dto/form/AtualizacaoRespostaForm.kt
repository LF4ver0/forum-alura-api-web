package br.com.alura.forum.dto.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class AtualizacaoRespostaForm(
    @field:NotNull
    val id: Long,

    @field:NotEmpty
    @field:Size(min = 5, max = 500)
    val mensagem: String,

    val solucao: Boolean
)