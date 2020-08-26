package br.com.softplan.pessoas.api.v2.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EnderecoInputV2 {

	private Long id;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String estado;
}
