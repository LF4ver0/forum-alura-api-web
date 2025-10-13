package br.com.alura.forum.mapper

import br.com.alura.forum.dto.view.RespostaView
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper : Mapper<Resposta, RespostaView> {

    override fun map(r: Resposta): RespostaView {
        return RespostaView(
            id = r.id,
            idAutor = r.autor.id!!,
            idTopico = r.topico.id!!,
            mensagem = r.mensagem,
            solucao = r.solucao
        )
    }
}