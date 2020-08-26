package br.com.softplan.pessoas.api.v1.openapi;

import br.com.softplan.pessoas.api.v1.model.SourceModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Código-fonte")
public interface SourceControllerOpenApi {

	@ApiOperation("Obtém os repositórios das aplicações")
    public SourceModel source() throws Exception;
}
