package com.br.desafio.votacao.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaResponseDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String dataInicio;
	
}
