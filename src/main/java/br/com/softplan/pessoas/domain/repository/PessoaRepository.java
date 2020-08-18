package br.com.softplan.pessoas.domain.repository;

import br.com.softplan.pessoas.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
