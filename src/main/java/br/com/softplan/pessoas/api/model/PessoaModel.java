package br.com.softplan.pessoas.api.model;

import br.com.softplan.pessoas.domain.model.Genero;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Relation("pessoas")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaModel extends RepresentationModel<PessoaModel> {

    private Long id;

    private String nome;

    private Genero sexo;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dataAlteracao;
}
