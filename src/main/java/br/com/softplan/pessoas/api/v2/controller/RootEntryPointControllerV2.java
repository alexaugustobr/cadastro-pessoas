package br.com.softplan.pessoas.api.v2.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.pessoas.api.v2.openapi.RootEntryPointControllerOpenApiV2;


@RestController
@RequestMapping("/v2")
public class RootEntryPointControllerV2 implements RootEntryPointControllerOpenApiV2 {
    
    @GetMapping(value="")
    public RootEntryPointModel root() {

        var root = new RootEntryPointModel();

        root.add(linkTo(PessoaControllerV2.class).withRel("pessoas"));

        return root;
    }
    
    
    public static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
