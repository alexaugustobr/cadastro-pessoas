package br.com.softplan.pessoas.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SourceModel {

	private String api;
	
	private String front;
}
