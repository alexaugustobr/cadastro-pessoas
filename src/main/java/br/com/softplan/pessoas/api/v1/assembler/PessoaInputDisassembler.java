package br.com.softplan.pessoas.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softplan.pessoas.api.v1.model.PessoaInput;
import br.com.softplan.pessoas.domain.model.Pessoa;

@Component
public class PessoaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pessoa toDomainObject(PessoaInput pessoaInput) {
        return modelMapper.map(pessoaInput, Pessoa.class);
    }

    public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {
        modelMapper.map(pessoaInput, pessoa);
    }
}
