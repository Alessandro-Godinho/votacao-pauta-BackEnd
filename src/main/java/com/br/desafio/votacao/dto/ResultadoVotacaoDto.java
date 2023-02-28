package com.br.desafio.votacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoDto {
	
	private Long totalSim;
	private Long totalNao;
	private Long totalVotos;

}
