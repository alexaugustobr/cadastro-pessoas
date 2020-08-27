package br.com.softplan.pessoas.api.v2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.pessoas.api.v2.model.SourceModelV2;
import br.com.softplan.pessoas.api.v2.openapi.SourceControllerOpenApiV2;

@RestController
@RequestMapping("/v2/source")
public class SourceControllerV2 implements SourceControllerOpenApiV2 {

    @Value("${source.code.api.url}")
    private String sourceCodeUrl;

    @Value("${source.code.front.url}")
    private String sourceCodeFrontUrl;

    @GetMapping
    public SourceModelV2 source() throws Exception {
  
    	return new SourceModelV2(this.sourceCodeUrl, this.sourceCodeFrontUrl);
    }
}
