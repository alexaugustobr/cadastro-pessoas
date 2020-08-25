package br.com.softplan.pessoas.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.softplan.pessoas.domain.exception.RecursoNaoEncontradoException;
import br.com.softplan.pessoas.domain.model.Genero;
import br.com.softplan.pessoas.domain.model.Pessoa;
import br.com.softplan.pessoas.domain.repository.PessoaRepository;
import br.com.softplan.pessoas.domain.service.PessoaService;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaService pessoaService;
	
	@Mock
	private PessoaRepository pessoaRepository;

	private static final Long ID_PESSOA_INEXISTENTE = -1L;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void deveRetornarListaComDuasPessoas_QuandoObterTodas() {
		
		when(this.pessoaRepository.findAll()).thenReturn(this.todasPessoas());
		
		List<Pessoa> pessoas = this.pessoaService.obterTodos();

		assertThat(pessoas, hasSize(2));
		assertThat(pessoas, hasItems(fulano(), beltrano()));
	}
	
	@Test
	public void deveRetornarFulano_QuandoObterPorId() {
		
		when(this.pessoaRepository.findById(fulano().getId())).thenReturn(Optional.of(fulano()));
		
		Pessoa pessoa = this.pessoaService.obterPorId(fulano().getId());
		
		assertThat(pessoa, is(notNullValue()));
	}
	
	@Test
	public void deveRetornarCiclano_QuandoCriarNovaPessoa() {
		
		when(this.pessoaRepository.save(any(Pessoa.class))).thenReturn(ciclano());
		
		Pessoa pessoa = this.pessoaService.salvar(ciclano());
		
		assertThat(pessoa, is(notNullValue()));
		assertThat(pessoa, equalTo(ciclano()));
	}
	
	@Test
	public void deveRetornarCiclanoComNovaDataAtualizacao_QuandoAlterar() {
		
		when(this.pessoaRepository.findById(ciclano().getId())).thenReturn(Optional.of(ciclano()));
		
		Pessoa ciclano = ciclano();
		
		Pessoa pessoa = this.pessoaService.atualizar(ciclano.getId(), ciclano);
		
		assertThat(pessoa, is(notNullValue()));
		assertThat(pessoa.getDataAlteracao(), is(greaterThan(ciclano.getDataAlteracao())));
		
		verify(this.pessoaRepository).findById(ciclano.getId());
	}
	
	@Test
	public void deveRemoverPessoa_QuandoPassarId() {
		
		when(this.pessoaRepository.findById(ciclano().getId())).thenReturn(Optional.of(ciclano()));
		
		this.pessoaService.remover(ciclano().getId());

		verify(this.pessoaRepository).findById(ciclano().getId());
		verify(this.pessoaRepository).delete(ciclano());
	}
	
	@Test
	public void deveLancarRecursoNaoEncontrado_QuandoObterPorIdInexistente() {
		
		assertThrows(RecursoNaoEncontradoException.class, () -> this.pessoaService.obterPorId(ID_PESSOA_INEXISTENTE));
	}
	
	private List<Pessoa> todasPessoas() {
		
		return Arrays.asList(fulano(), beltrano());
	}
	
	private Pessoa fulano() {
		
		Pessoa fulano = new Pessoa();
		fulano.setId(1L);
		fulano.setCpf("773.554.880-07");
		fulano.setDataAlteracao(LocalDateTime.now());
		fulano.setDataCadastro(LocalDateTime.now());
		fulano.setDataNascimento(LocalDate.of(1998, 10, 10));
		fulano.setEmail("fulano@email.com");
		fulano.setNacionalidade("Brasileiro");
		fulano.setNaturalidade("Mocoquense");
		fulano.setNome("Fulano");
		fulano.setSexo(Genero.MASCULINO);

		return fulano;
	}
	
	private Pessoa beltrano() {
		
		Pessoa beltrano = new Pessoa();
		beltrano.setId(2L);
		beltrano.setCpf("256.926.320-63");
		beltrano.setDataAlteracao(LocalDateTime.now());
		beltrano.setDataCadastro(LocalDateTime.now());
		beltrano.setDataNascimento(LocalDate.of(1998, 10, 10));
		beltrano.setEmail("beltrano@email.com");
		beltrano.setNacionalidade("Brasileiro");
		beltrano.setNaturalidade("Mocoquense");
		beltrano.setNome("Beltrano");
		beltrano.setSexo(Genero.MASCULINO);
		
		return beltrano;
	}
	
	private Pessoa ciclano() {
		
		Pessoa ciclano = new Pessoa();
		ciclano.setId(2L);
		ciclano.setCpf("484.425.470-70");
		ciclano.setDataAlteracao(LocalDateTime.now());
		ciclano.setDataCadastro(LocalDateTime.now());
		ciclano.setDataNascimento(LocalDate.of(1998, 10, 10));
		ciclano.setEmail("ciclano@email.com");
		ciclano.setNacionalidade("Brasileiro");
		ciclano.setNaturalidade("Mocoquense");
		ciclano.setNome("Ciclano");
		ciclano.setSexo(Genero.MASCULINO);
		
		return ciclano;
	}
}
