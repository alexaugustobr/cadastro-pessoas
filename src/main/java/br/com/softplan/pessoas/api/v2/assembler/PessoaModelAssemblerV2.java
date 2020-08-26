package br.com.softplan.pessoas.api.v2.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.softplan.pessoas.api.v1.controller.PessoaController;
import br.com.softplan.pessoas.api.v2.controller.PessoaControllerV2;
import br.com.softplan.pessoas.api.v2.model.PessoaModelV2;
import br.com.softplan.pessoas.domain.model.Pessoa;

@Component
public class PessoaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Pessoa, PessoaModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    public PessoaModelAssemblerV2() {
        super(PessoaControllerV2.class, PessoaModelV2.class);
    }

    @Override
    public PessoaModelV2 toModel(Pessoa pessoa) {

        PessoaModelV2 pessoaModel = this.createModelWithId(pessoa.getId(), pessoa);
        System.out.println(pessoaModel);

        this.modelMapper.map(pessoa, pessoaModel);
        System.out.println(pessoaModel);

        pessoaModel.add(linkTo(PessoaController.class)
                        .withRel("pessoas"));

        return pessoaModel;
    }

    @Override
    public CollectionModel<PessoaModelV2> toCollectionModel(Iterable<? extends Pessoa> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(PessoaController.class)
                		.withRel(IanaLinkRelations.SELF));
    }
}
