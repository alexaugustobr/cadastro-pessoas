package br.com.softplan.pessoas.api.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/")
public class RootEntryPointController {
    
    @GetMapping(value="")
    public RootEntryPointModel root() {

        var root = new RootEntryPointModel();

        root.add(linkTo(PessoaController.class).withRel("pessoas"));

        return root;
    }
    
    
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
