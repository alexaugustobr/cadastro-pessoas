package br.com.softplan.pessoas.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocketV1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.softplan.pessoas.api"))
					.paths(PathSelectors.ant("/v1/**"))
					.build();
	}

	@Bean
	public Docket apiDocketV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V2")
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.softplan.pessoas.api"))
					.paths(PathSelectors.ant("/v2/**"))
					.build();
	}
}
