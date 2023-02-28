package com.br.desafio.votacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VotosDto {
	
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "identificação do associado", example = "1")
	private Long idAssociado;
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "identificação da pauta", example = "2")
	private Long idPauta;
	@NotEmpty
	@NotBlank
	@ApiModelProperty(notes = "Voto do associado (apenas sim ou não", example = "Sim")
	private Boolean voto;
	
}
