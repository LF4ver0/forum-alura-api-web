package br.com.alura.forum.service

import br.com.alura.forum.dto.form.AtualizacaoRespostaForm
import br.com.alura.forum.dto.form.RespostaForm
import br.com.alura.forum.dto.view.RespostaView
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class RespostasService(
    private var respostas: List<Resposta>,
    private val topicoService: TopicoService,
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper
) {
    fun listarRespostasPorTopico(idTopico: Long): List<Resposta> {
        return respostas.filter { it.topico.id == idTopico }
    }


    fun cadastrarResposta(idTopico: Long, form: RespostaForm) {
        val novaResposta = respostaFormMapper.map(form).apply {
            id = respostas.size.toLong() + 1
            topico = topicoService.obterTopico(idTopico)
        }
        respostas += novaResposta
    }

    fun atualizar(form: AtualizacaoRespostaForm): RespostaView {
        val respostaOriginal = respostas.firstOrNull { it.id == form.id }
            ?: throw NoSuchElementException("Resposta com id ${form.id} não encontrada")

        val respostaAtualizada = respostaOriginal.copy(
            mensagem = form.mensagem,
            solucao = form.solucao
        )
        respostas = respostas.minus(respostaOriginal).plus(respostaAtualizada)

        return respostaViewMapper.map(respostaAtualizada)
    }

    fun deletar(id: Long) {
        val resposta = respostas.find { it.id == id}
            ?: throw NoSuchElementException("Resposta com Id ${id} não Encontrada !")
        respostas = respostas.minus(resposta)
    }


}