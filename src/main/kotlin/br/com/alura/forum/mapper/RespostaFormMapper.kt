package br.com.alura.forum.mapper


import br.com.alura.forum.dto.form.RespostaForm
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component


@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService,
) : Mapper<RespostaForm, Resposta> {

    override fun map(r: RespostaForm): Resposta {
        return Resposta(
            mensagem = r.mensagem,
            autor = usuarioService.buscaPorId(r.idAutor)!!,
            topico = topicoService.obterTopico(r.idTopico),
            solucao = r.solucao
        )
    }
}
