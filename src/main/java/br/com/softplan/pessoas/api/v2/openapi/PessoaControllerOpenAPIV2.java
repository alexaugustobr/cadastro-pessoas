package br.com.softplan.pessoas.api.v2.openapi;

import org.springframework.hateoas.CollectionModel;

import br.com.softplan.pessoas.api.exception.Problem;
import br.com.softplan.pessoas.api.v2.model.PessoaInputV2;
import br.com.softplan.pessoas.api.v2.model.PessoaModelV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pessoas")
public interface PessoaControllerOpenAPIV2 {


    @ApiOperation(value = "Listagem de pessoas", response = PessoaModelV2.class)
    public CollectionModel<PessoaModelV2> listar();

    @ApiOperation("Obtém pessoa pelo seu ID")
    @ApiResponses({
    	@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada", response = Problem.class)
    })
    public PessoaModelV2 buscarPorId(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id);

    @ApiOperation("Criar pessoa")
    @ApiResponses({
    	@ApiResponse(code = 201, message = "Pessoa cadastrada")
    })
    public PessoaModelV2 salvar(
    		@ApiParam(name = "corpo", value = "Representação de pessoa", required = true) PessoaInputV2 pessoaInput);

    @ApiOperation("Atualizar pessoa pelo ID")
    @ApiResponses({
    	@ApiResponse(code = 200, message = "Pessoa atualizada"),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada", response = Problem.class)
    })
    public PessoaModelV2 atualizar(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id, 
    		@ApiParam(name = "corpo", value = "Representação de pessoa", required = true) PessoaInputV2 pessoaInput);

    @ApiOperation("Remover pessoa por ID")
    @ApiResponses({
    	@ApiResponse(code = 204, message = "Pessoa removida"),
    	@ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    public void remover(
    		@ApiParam(name = "ID da pessoa", example = "1", required = true) Long id);

}
