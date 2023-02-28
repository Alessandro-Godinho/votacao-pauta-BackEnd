package com.br.desafio.votacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
	
	private Long id;
	private String nome;
}
