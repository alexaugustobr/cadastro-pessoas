package br.com.softplan.pessoas.api.v2.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.softplan.pessoas.domain.model.Genero;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Relation(value = "pessoa", collectionRelation = "pessoas")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaModelV2 extends RepresentationModel<PessoaModelV2> {

	@EqualsAndHashCode.Include
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
    
    private EnderecoInputV2 endereco;
}
