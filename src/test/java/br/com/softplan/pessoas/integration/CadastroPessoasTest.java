package br.com.softplan.pessoas.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.softplan.pessoas.domain.model.Genero;
import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.repository.PessoaRepository;
import br.com.softplan.pessoas.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroPessoasTest {
	@LocalServerPort
	private int port;

	@Autowired
	private PessoaRepository repository;

	private String jsonBeltranoCorreto = ResourceUtils.getContentFromResource(
			"/payloads/pessoas/correto/beltrano-post-payload.json");

	private String jsonFulanoPutCorreto = ResourceUtils.getContentFromResource(
			"/payloads/pessoas/correto/fulano-put-payload.json");

	private String jsonPessoaSemNome = ResourceUtils.getContentFromResource(
			"/payloads/pessoas/incorreto/pessoa-sem-nome-payload.json"
	);

	private String jsonPessoaCpfRepetido = ResourceUtils.getContentFromResource(
			"/payloads/pessoas/incorreto/pessoa-cpf-repetido-payload.json"
	);

	private String jsonPessoaEmailInvalido = ResourceUtils.getContentFromResource(
			"/payloads/pessoas/incorreto/pessoa-email-invalido-payload.json"
	);
	
	private Pessoa fulano;
	private Pessoa ciclana;

	private static final int QUANTIDADE_PESSOAS = 2;
	private static final int ID_PESSOA_INEXISTENTE = -1;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas";

		this.prepararDados();
	}

	@AfterEach
	public void tearDown() {
		this.limparTabelaPessoas();
	}

	@Test
	public void deveRetornar200_QuandoObterPessoas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("_embedded.pessoas.size()", is(QUANTIDADE_PESSOAS));
	}

	@Test
	public void deveRetornarFulanoEStatus200_QuandoObterPorId() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", this.fulano.getId())
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(this.fulano.getNome()));
	}

	@Test
	public void deveRetornar201_QuandoCriarPessoa() {
		given()
			.body(jsonBeltranoCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornar200_QuandoAtualizarPessoa() {
		given()
			.body(jsonFulanoPutCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{id}", this.fulano.getId())
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("naturalidade", is("Mocoquense"));
	}

	@Test
	public void deveRetornar204_QuandoRemoverPessoas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.delete("/{id}", this.fulano.getId())
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void deveRetornar404_QuandoObterPessoaInexistente() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/{id}", ID_PESSOA_INEXISTENTE)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void deveRetornar400_QuandoCadastrarPessoaSemNome() {
		given()
			.body(jsonPessoaSemNome)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornar400_QuandoCadastrarPessoaComEmailInvalido() {
		given()
			.body(jsonPessoaEmailInvalido)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornar409_QuandoCadastrarPessoaComCpfRepetido() {
		given()
			.body(jsonPessoaCpfRepetido)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CONFLICT.value());
	}

	private void prepararDados() {

		this.fulano = new Pessoa();
		this.fulano.setNome("Fulano");
		this.fulano.setCpf("773.554.880-07");
		this.fulano.setEmail("fulano@email.com");
		this.fulano.setNacionalidade("Brasileiro");
		this.fulano.setNaturalidade("Paulistano");
		this.fulano.setDataNascimento(LocalDate.of(1998, 3, 10));
		this.fulano.setSexo(Genero.MASCULINO);

		this.fulano = this.repository.save(this.fulano);

		this.ciclana = new Pessoa();
		this.ciclana.setNome("Ciclana");
		this.ciclana.setCpf("672.660.470-05");
		this.ciclana.setEmail("ciclana@email.com");
		this.ciclana.setNacionalidade("Brasileira");
		this.ciclana.setNaturalidade("Carioca");
		this.ciclana.setDataNascimento(LocalDate.of(1996, 9, 1));
		this.ciclana.setSexo(Genero.FEMININO);

		this.ciclana = this.repository.save(this.ciclana);
	}

	private void limparTabelaPessoas() {
		this.repository.deleteAll();
	}

}
