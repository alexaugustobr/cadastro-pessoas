package br.com.softplan.pessoas.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softplan.pessoas.api.v2.model.PessoaInputV2;
import br.com.softplan.pessoas.domain.model.Pessoa;

@Component
public class PessoaInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Pessoa toDomainObject(PessoaInputV2 pessoaInput) {
        return modelMapper.map(pessoaInput, Pessoa.class);
    }

    public void copyToDomainObject(PessoaInputV2 pessoaInput, Pessoa pessoa) {
        modelMapper.map(pessoaInput, pessoa);
    }
}
