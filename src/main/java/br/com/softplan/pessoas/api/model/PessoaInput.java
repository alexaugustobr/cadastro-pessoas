package br.com.softplan.pessoas.api.model;

import br.com.softplan.pessoas.domain.model.Genero;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PessoaInput {

    private Long id;

    @NotEmpty
    private String nome;

    private Genero sexo;

    @Email
    private String email;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    @CPF
    @NotEmpty
    private String cpf;
}
