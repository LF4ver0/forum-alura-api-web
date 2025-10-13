package br.com.alura.forum.controller

import br.com.alura.forum.dto.form.AtualizacaoRespostaForm
import br.com.alura.forum.dto.form.RespostaForm
import br.com.alura.forum.dto.view.RespostaView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostasService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/topicos/{id}/respostas")
class RespostasController(private val service: RespostasService) {

    @GetMapping
    fun listarRespostasPorTopico(@PathVariable id: Long): List<Resposta> {
        return this.service.listarRespostasPorTopico(id)
    }

    @PostMapping
    fun cadastrar(@PathVariable id: Long, @RequestBody @Valid dto: RespostaForm) {
        service.cadastrarResposta(id, dto)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid form: AtualizacaoRespostaForm): ResponseEntity<RespostaView> {
        val respostaView = service.atualizar(form)
        return ResponseEntity.ok(respostaView)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // retorno 204, deleção feita com sucesso
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }


}