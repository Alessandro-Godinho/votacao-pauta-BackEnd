package com.br.desafio.votacao.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.votacao.dto.ResultadoVotacaoDto;
import com.br.desafio.votacao.dto.VotosDto;
import com.br.desafio.votacao.service.VotacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("votacao")
@RequiredArgsConstructor
@Api(value = "Votação da Pauta pelo Associado API")
public class VotacaoController {
	
	private final VotacaoService votacaoService;

	@ApiOperation(value = "Votação de Pauta")
	@PostMapping
    public ResponseEntity<VotosDto> voto(@Valid @RequestBody VotosDto votoDto ){
		votacaoService.votar(votoDto);
        return ResponseEntity.ok(votoDto);
    }
	
	@ApiOperation(value = "Resultado da votação")
	@GetMapping("/pauta/{id}")
    public ResponseEntity<ResultadoVotacaoDto> resultado(@PathVariable("id") Long idPauta){
        return ResponseEntity.ok(votacaoService.resultado(idPauta));
    }

}
