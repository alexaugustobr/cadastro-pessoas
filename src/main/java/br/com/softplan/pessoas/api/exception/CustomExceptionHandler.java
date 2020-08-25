package br.com.softplan.pessoas.api.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.softplan.pessoas.domain.exception.RecursoNaoEncontradoException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = "Um ou mais campos estão inválidos.";

        List<Problem.Object> problemObjects = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDetalhes(detail);
        problem.setTimestamp(LocalDateTime.now());
        problem.setObjects(problemObjects);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(
            RecursoNaoEncontradoException ex, WebRequest request) {
        return this.handle(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        return this.handle(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return this.handle(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    private ResponseEntity<Object> handle(
            HttpStatus status,
            Exception ex,
            WebRequest request) {
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
