package com.br.desafio.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpiracaoPautaDto {
	
	private Boolean tempoExpirado;
	private Long minutosRestante;

}
