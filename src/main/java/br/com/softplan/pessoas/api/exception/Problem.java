package br.com.softplan.pessoas.api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Problem {

    private int status;

    private String titulo;

    private LocalDateTime timestamp;

    private String detalhes;
}
