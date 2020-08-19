package br.com.softplan.pessoas.api.assembler;

import br.com.softplan.pessoas.api.model.PessoaInput;
import br.com.softplan.pessoas.domain.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
