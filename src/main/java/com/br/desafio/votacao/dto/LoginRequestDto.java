package com.br.desafio.votacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDto {
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Cpf do associado", example = "07815498521")
	private String cpf;
}
