package com.br.socialbookapi.hanlder;

import com.br.socialbookapi.domain.ErrorsDetails;
import com.br.socialbookapi.services.exceptions.LivroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResoucerExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErrorsDetails> handleLivroNaoEncontradoException (LivroNaoEncontradoException e, HttpServletRequest request) {

        ErrorsDetails erro = new ErrorsDetails();
        erro.setTitulo("O livro não pôde ser encontrado.");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
        erro.setStatus(404L);
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
