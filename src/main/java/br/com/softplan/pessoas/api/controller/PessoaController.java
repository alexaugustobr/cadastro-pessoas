package br.com.softplan.pessoas.api.controller;

import br.com.softplan.pessoas.api.assembler.PessoaInputDisassembler;
import br.com.softplan.pessoas.api.assembler.PessoaModelAssembler;
import br.com.softplan.pessoas.api.model.PessoaInput;
import br.com.softplan.pessoas.api.model.PessoaModel;
import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    private PessoaModelAssembler assembler;

    private PessoaInputDisassembler disassembler;

    public PessoaController(PessoaService pessoaService,
                            PessoaModelAssembler assembler,
                            PessoaInputDisassembler disassembler) {
        this.pessoaService = pessoaService;
        this.assembler = assembler;
        this.disassembler = disassembler;
    }

    @GetMapping
    public CollectionModel<PessoaModel> listar() {
        List<Pessoa> pessoas = this.pessoaService.obterTodos();

        return this.assembler.toCollectionModel(pessoas);
    }

    @GetMapping("/{id}")
    public PessoaModel buscarPorId(@PathVariable("id") Long id) {
        Pessoa pessoa = this.pessoaService.obterPorId(id);

        return this.assembler.toModel(pessoa);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaModel salvar(@RequestBody @Valid PessoaInput pessoaInput) {

        Pessoa pessoa = this.disassembler.toDomainObject(pessoaInput);

        Pessoa pessoaSalva = this.pessoaService.salvar(pessoa);

        addUriInResponseHeader(pessoaSalva.getId());

        return this.assembler.toModel(pessoaSalva);
    }

    @PutMapping("/{id}")
    public PessoaModel atualizar(@PathVariable("id") Long id, @RequestBody @Valid PessoaInput pessoaInput) {

        Pessoa pessoa = this.disassembler.toDomainObject(pessoaInput);

        Pessoa pessoaAtualizada = this.pessoaService.atualizar(id, pessoa);

        return this.assembler.toModel(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long id) {
        this.pessoaService.remover(id);
    }

    private void addUriInResponseHeader(Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id).toUri();

        HttpServletResponse response = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();

        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
