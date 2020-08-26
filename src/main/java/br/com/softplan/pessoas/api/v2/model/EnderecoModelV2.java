package br.com.softplan.pessoas.api.v2.model;

import lombok.Data;

@Data
public class EnderecoModelV2 {

	private Long id;
	
	private String cep;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
}
