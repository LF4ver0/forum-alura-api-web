package br.com.alura.forum.service

import br.com.alura.forum.dto.form.AtualizacaoTopicoForm
import br.com.alura.forum.dto.form.NovoTopicoForm
import br.com.alura.forum.dto.view.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

import kotlin.NoSuchElementException

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não Encontrado"
    ) {

    fun listar(): List<TopicoView>{
        return topicos.map { it -> topicoViewMapper.map(it)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.find { it.id == id }
            ?: throw NotFoundException(notFoundMessage)

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.find { it.id == form.id }
            ?: throw NotFoundException(notFoundMessage)

        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
        /*
            Nesse caso como os Tópicos(Registros que estariam no BD) estão em memória,
            é preciso excluir o topico informado da lista (utilizando minus) e depois
            adicionaá-lo novamente com as informações atualizadas (utilizando plus).
            As que não forma atualizado recuperados do registro "antigo".
        */
    }

    fun deletar(id: Long) {
        val topico = topicos.find { it.id == id }
            ?: throw NotFoundException(notFoundMessage)
        topicos = topicos.minus(topico)

        /*
            Semelhante a lógica de atualização
        */
    }

    fun obterTopico(id: Long): Topico {
        return topicos.firstOrNull { it.id == id }
            ?: throw NotFoundException(notFoundMessage)
    }

}