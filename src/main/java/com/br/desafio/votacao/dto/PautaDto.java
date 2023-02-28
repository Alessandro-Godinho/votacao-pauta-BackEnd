package com.br.desafio.votacao.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaDto {
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Id da pauta", example = "8")
	private Long id;
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Titulo da pauta", example = "Mundial do Palmeiras")
	private String titulo;
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Breve descrição da pauta", example = "Voce considera que o Palmeiras tem mundial?")
	private String descricao;
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Data inicio da pauta", example = "2023-02-09T00:00:00")
	private String dataInicio;
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Tempo expiração da pauta", example = "2023-02-09T00:10:00")
	private String dataFim;
	
}
