package com.br.desafio.votacao.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.dto.AssociadoDto;
import com.br.desafio.votacao.repository.AssociadoRepository;

class AssociadoServiceTest {
	
	@Mock
	private AssociadoRepository associadoRepository;
	
	@InjectMocks
	private AssociadoService associadoService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void shouldSaveAssociado() {
		
		AssociadoDto associadoDto = new AssociadoDto();
        associadoDto.setNome("John Doe");
        associadoDto.setCpf("12345678901");
        
        Associado associado = new Associado();
        associado.setNome("John Doe");
        associado.setCpf("12345678901");
		
		when(associadoRepository.save(associado)).thenReturn(associado);
		
		Associado savedAssociado = associadoService.save(associadoDto);
		
		assertThat(savedAssociado).isEqualTo(associado);
	}
}