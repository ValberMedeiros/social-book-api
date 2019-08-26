package com.br.socialbookapi.resources;

import com.br.socialbookapi.domain.Comentario;
import com.br.socialbookapi.domain.Livro;
import com.br.socialbookapi.services.LivrosService;
import com.br.socialbookapi.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivrosResource {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros = livrosService.listar();

        if(livros.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Livro livroPesquisa = livrosService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroPesquisa);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Livro livro) {
        livro = livrosService.salvar(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);
        livrosService.atualizar(livro);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        livrosService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Void> adicionarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        livrosService.salvarComentario(id, comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long id) {
        List<Comentario> comentarios = livrosService.listarComentarios(id);

        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }

}
