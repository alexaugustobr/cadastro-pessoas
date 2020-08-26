package br.com.softplan.pessoas.api.v2.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SourceModelV2 {

	private String api;
	private String front;
}
