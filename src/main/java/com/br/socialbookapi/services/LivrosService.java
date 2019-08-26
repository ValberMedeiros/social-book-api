package com.br.socialbookapi.services;

import com.br.socialbookapi.domain.Comentario;
import com.br.socialbookapi.domain.Livro;
import com.br.socialbookapi.repository.ComentariosRepository;
import com.br.socialbookapi.repository.LivrosRepository;
import com.br.socialbookapi.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private ComentariosRepository comentariosRepository;

    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    public Livro buscar (Long id) {
        Livro livroPesquisa = livrosRepository.findById(id).orElse(null);

        if(livroPesquisa == null){
            throw new LivroNaoEncontradoException("O livro pôde ser encontrado.");
        }
        return livroPesquisa;
    }

    public Livro salvar(Livro livro) {
        livro.setId(null);
        livro = livrosRepository.save(livro);

        return livro;
    }

    public void deletar(Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LivroNaoEncontradoException("O livro pôde ser encontrado.");
        }
    }

    public void atualizar(Livro livro) {
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }

    public Comentario salvarComentario(Long livroId, Comentario comentario) {
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(LocalDate.now());

        return comentariosRepository.save(comentario);
    }

    public List<Comentario> listarComentarios (Long livroId) {
        Livro livro = buscar(livroId);
        List<Comentario> comentarios = livro.getComentarios();

        return comentarios;
    }
}
