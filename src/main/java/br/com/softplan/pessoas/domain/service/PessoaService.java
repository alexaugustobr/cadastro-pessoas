package br.com.softplan.pessoas.domain.service;

import br.com.softplan.pessoas.domain.exception.RecursoNaoEncontradoException;
import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<Pessoa> obterTodos() {
        return this.pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pessoa obterPorId(Long id) {
        return this.pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada."));
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    @Transactional
    public void remover(Long id) {
        this.pessoaRepository.delete(
                this.obterPorId(id));
    }
}