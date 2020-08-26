package br.com.softplan.pessoas.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pessoas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @Enumerated(value = EnumType.STRING)
    private Genero sexo;

    @Email(regexp = ".*@.*\\..*")
    private String email;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    @CPF
    @NotEmpty
    @Column(unique = true)
    private String cpf;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAlteracao;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}
