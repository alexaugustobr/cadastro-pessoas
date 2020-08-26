package br.com.softplan.pessoas.api.v2.model;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.softplan.pessoas.domain.model.Genero;
import lombok.Data;

@Data
public class PessoaInputV2 {

    private Long id;

    @NotEmpty
    private String nome;

    private Genero sexo;

    @Email(regexp=".*@.*\\..*")
    private String email;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    @CPF
    @NotEmpty
    private String cpf;

    @Valid
    @NotNull
    private EnderecoInputV2 endereco;
}
