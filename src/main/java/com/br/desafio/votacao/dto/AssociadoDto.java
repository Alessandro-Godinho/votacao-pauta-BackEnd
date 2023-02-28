package com.br.desafio.votacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AssociadoDto {
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Nome do associado", example = "Eduardo")
	private String nome;
	@NotEmpty
	@NotBlank
	@Size(max = 11, min = 11)
	@ApiModelProperty(notes = "Cpf do associado (apenas numeros)", example = "07815498521")
	private String cpf;
	
}
