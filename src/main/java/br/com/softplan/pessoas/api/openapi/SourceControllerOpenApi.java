package br.com.softplan.pessoas.api.openapi;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Código-fonte")
public interface SourceControllerOpenApi {

	@ApiOperation("Obtém os repositórios das aplicações")
    public void source(HttpServletResponse response) throws Exception;
}
