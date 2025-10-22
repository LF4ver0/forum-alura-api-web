package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.form.AtualizacaoTopicoForm
import br.com.alura.forum.dto.form.NovoTopicoForm
import br.com.alura.forum.dto.view.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não Encontrado"
    ) {

    //Forma "Clássica"
/*    fun listar(nomeCurso: String?): List<TopicoView>{
        val topicos = if(nomeCurso == null){
            repository.findAll()
        } else {
            repository.findByCursoNome(nomeCurso)
        }
        return topicos.map{ it -> topicoViewMapper.map(it)
        }
    }
*/

    //Forma Moderna no Kotlin
    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> =
        (nomeCurso?.let {repository.findByCursoNome(it, paginacao)} ?: repository.findAll(paginacao))
            .map(topicoViewMapper::map)


    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun obterTopico(id: Long): Topico {
        return repository.findById(id).orElseThrow{throw NotFoundException(notFoundMessage)}
    }

    fun relatorio(): List<TopicoPorCategoriaDto>{
        return repository.relatorio()
    }

}