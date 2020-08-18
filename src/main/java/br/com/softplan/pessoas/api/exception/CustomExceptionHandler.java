package br.com.softplan.pessoas.api.exception;

import br.com.softplan.pessoas.domain.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String titulo = status.getReasonPhrase();
        String detalhes = ex.getMessage();
        LocalDateTime timestamp = LocalDateTime.now();

        Problem problem = new Problem();
        problem.setDetalhes(detalhes);
        problem.setStatus(status.value());
        problem.setTitulo(titulo);
        problem.setTimestamp(timestamp);

        return this.handleExceptionInternal(ex, problem, null, status, request);
    }
}
