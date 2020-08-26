package br.com.softplan.pessoas.api.v1.openapi;

import br.com.softplan.pessoas.api.v1.controller.RootEntryPointController.RootEntryPointModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Ponto de entrada da API")
public interface RootEntryPointControllerOpenApi {

	@ApiOperation("Obtém os endpoints da API")
    public RootEntryPointModel root();
}
