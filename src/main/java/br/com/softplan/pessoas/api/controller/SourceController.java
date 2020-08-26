package br.com.softplan.pessoas.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.pessoas.api.openapi.SourceControllerOpenApi;

@RestController
@RequestMapping("/source")
public class SourceController implements SourceControllerOpenApi {

    @Value("${app.source.code.url}")
    private String sourceCodeUrl;

    @GetMapping
    public void source(HttpServletResponse response) throws Exception {
       response.sendRedirect(sourceCodeUrl);
    }
}
