package br.com.softplan.pessoas.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pessoas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(value = EnumType.STRING)
    private Genero sexo;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    @Column(unique = true)
    private String cpf;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAlteracao;
}
