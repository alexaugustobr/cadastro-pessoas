package br.com.softplan.pessoas.api.v2.openapi;

import br.com.softplan.pessoas.api.v2.controller.RootEntryPointControllerV2.RootEntryPointModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Ponto de entrada da API")
public interface RootEntryPointControllerOpenApiV2 {

	@ApiOperation("Obtém os endpoints da API")
    public RootEntryPointModel root();
}
