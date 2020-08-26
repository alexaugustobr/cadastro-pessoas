package br.com.softplan.pessoas.api.openapi;

import org.springframework.hateoas.CollectionModel;

import br.com.softplan.pessoas.api.exception.Problem;
import br.com.softplan.pessoas.api.model.PessoaInput;
import br.com.softplan.pessoas.api.model.PessoaModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pessoas")
public interface PessoaControllerOpenAPI {

    @ApiOperation(value = "Listagem de pessoas", response = PessoaModel.class)
    public CollectionModel<PessoaModel> listar();

    @ApiOperation("Obtém pessoa pelo seu ID")
    @ApiResponses({
    	@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada", response = Problem.class)
    })
    public PessoaModel buscarPorId(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id);

    @ApiOperation("Criar pessoa")
    @ApiResponses({
    	@ApiResponse(code = 201, message = "Pessoa cadastrada")
    })
    public PessoaModel salvar(
    		@ApiParam(name = "corpo", value = "Representação de pessoa", required = true) PessoaInput pessoaInput);

    @ApiOperation("Atualizar pessoa pelo ID")
    @ApiResponses({
    	@ApiResponse(code = 200, message = "Pessoa atualizada"),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada", response = Problem.class)
    })
    public PessoaModel atualizar(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id, 
    		@ApiParam(name = "corpo", value = "Representação de pessoa", required = true) PessoaInput pessoaInput);

    @ApiOperation("Remover pessoa por ID")
    @ApiResponses({
    	@ApiResponse(code = 204, message = "Pessoa removida"),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    public void remover(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id);

}
