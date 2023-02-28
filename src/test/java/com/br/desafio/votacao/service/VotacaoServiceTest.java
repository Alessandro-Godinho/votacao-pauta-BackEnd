package com.br.desafio.votacao.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.Votacao;
import com.br.desafio.votacao.dto.VotosDto;
import com.br.desafio.votacao.exception.RegistroNaoEncontradoException;
import com.br.desafio.votacao.repository.AssociadoRepository;
import com.br.desafio.votacao.repository.PautaRepository;
import com.br.desafio.votacao.repository.VotacaoRepository;

@ExtendWith(MockitoExtension.class)
public class VotacaoServiceTest {

	@Mock
	private PautaRepository pautaRepository;
	@Mock
	private VotacaoRepository votacaoRepository;
	@Mock
	private AssociadoRepository associadoRepository;
	
	@InjectMocks
	private VotacaoService votacaoService;
	
	private VotosDto votosDto;
	private Pauta pauta;
	private Associado associado;
	private Votacao votacao;
	
	@BeforeEach
	public void setup() {
		votosDto = new VotosDto(1L, 1L, true);
		pauta = Pauta.builder().id(1L).build();
		associado = Associado.builder().id(1L).build();
		votacao = Votacao.builder().associado(associado).pauta(pauta).voto(true).build();
	}
	
	@Test
	public void testVotar() {
		when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
		when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
		when(votacaoRepository.findByAssociadoIdAndPautaId(1L, 1L)).thenReturn(List.of());
		when(votacaoRepository.save(votacao)).thenReturn(votacao);
		
		votacaoService.votar(votosDto);
		
		verify(pautaRepository, times(1)).findById(1L);
		verify(associadoRepository, times(1)).findById(1L);
		verify(votacaoRepository, times(1)).findByAssociadoIdAndPautaId(1L, 1L);
		verify(votacaoRepository, times(1)).save(votacao);
	}
	@Test
	public void testVotar_pautaNaoEncontrada() {
		when(pautaRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(RegistroNaoEncontradoException.class, () -> votacaoService.votar(votosDto));
		
		verify(pautaRepository, times(1)).findById(1L);
		verify(associadoRepository, times(0)).findById(1L);
		verify(votacaoRepository, times(0)).findByAssociadoIdAndPautaId(1L, 1L);
		verify(votacaoRepository, times(0)).save(votacao);
		
	}
	
}