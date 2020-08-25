package br.com.softplan.pessoas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.pessoas.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
