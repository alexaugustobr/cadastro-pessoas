package br.com.softplan.pessoas.api.v1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.pessoas.api.v1.model.SourceModel;
import br.com.softplan.pessoas.api.v1.openapi.SourceControllerOpenApi;

@RestController
@RequestMapping("/v1/source")
public class SourceController implements SourceControllerOpenApi {

    @Value("${source.code.api.url}")
    private String sourceCodeUrl;

    @Value("${source.code.front.url}")
    private String sourceCodeFrontUrl;

    @GetMapping
    public SourceModel source() throws Exception {    
       
    	return new SourceModel(this.sourceCodeUrl, this.sourceCodeFrontUrl);
    }
}
