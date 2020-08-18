package br.com.softplan.pessoas.api.controller;

import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> listar() {
        return this.pessoaService.obterTodos();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable("id") Long id) {
        return this.pessoaService.obterPorId(id);
    }

    @PostMapping
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return this.pessoaService.salvar(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {

        return this.pessoaService.salvar(pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long id) {
        this.pessoaService.remover(id);
    }
}
