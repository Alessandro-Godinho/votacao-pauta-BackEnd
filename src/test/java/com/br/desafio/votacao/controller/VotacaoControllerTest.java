package com.br.desafio.votacao.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.br.desafio.votacao.dto.ResultadoVotacaoDto;
import com.br.desafio.votacao.dto.VotosDto;
import com.br.desafio.votacao.service.VotacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VotacaoController.class)
public class VotacaoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VotacaoService votacaoService;
	
	@Test
	public void testVoto() throws Exception {
		VotosDto votoDto = VotosDto.builder()
				.idAssociado(1l)
				.idPauta(2l)
				.voto(true)
				.build();
		
		mockMvc.perform(post("/votacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(votoDto)))
				.andExpect(status().isOk());
		
		verify(votacaoService, times(1)).votar(any(VotosDto.class));
	}
	
	@Test
	public void testResultado() throws Exception {
		ResultadoVotacaoDto resultado = ResultadoVotacaoDto.builder()
				.totalNao(2l)
				.totalSim(3l)
				.totalVotos(5l)
				.build();
		when(votacaoService.resultado(1L)).thenReturn(resultado);
		
		mockMvc.perform(get("/votacao/pauta/1"))
				.andExpect(status().isOk());
		
		verify(votacaoService, times(1)).resultado(1L);
	}
}