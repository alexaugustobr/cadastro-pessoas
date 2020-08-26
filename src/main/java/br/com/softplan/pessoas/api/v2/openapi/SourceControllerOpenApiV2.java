package br.com.softplan.pessoas.api.v2.openapi;

import br.com.softplan.pessoas.api.v2.model.SourceModelV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Código-fonte")
public interface SourceControllerOpenApiV2 {

	@ApiOperation("Obtém os repositórios das aplicações")
    public SourceModelV2 source() throws Exception;
}
