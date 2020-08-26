package br.com.softplan.pessoas.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

	private String cep;
	
	private String logradouro;

	private String bairro;
	
	private String cidade;
	
	private String estado;
	
}
