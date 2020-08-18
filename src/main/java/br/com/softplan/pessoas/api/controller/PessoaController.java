package br.com.softplan.pessoas.api.controller;

import br.com.softplan.pessoas.api.assembler.PessoaModelAssembler;
import br.com.softplan.pessoas.api.model.PessoaModel;
import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    private PessoaModelAssembler assembler;

    public PessoaController(PessoaService pessoaService, PessoaModelAssembler assembler) {
        this.pessoaService = pessoaService;
        this.assembler = assembler;
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
    public PessoaModel salvar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = this.pessoaService.salvar(pessoa);

        return this.assembler.toModel(pessoaSalva);
    }

    @PutMapping("/{id}")
    public PessoaModel atualizar(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {

        Pessoa pessoaAtualizada = this.pessoaService.atualizar(id, pessoa);

        return this.assembler.toModel(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long id) {
        this.pessoaService.remover(id);
    }
}
