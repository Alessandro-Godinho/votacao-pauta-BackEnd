package com.br.desafio.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.votacao.dto.AssociadoDto;
import com.br.desafio.votacao.dto.LoginDto;
import com.br.desafio.votacao.dto.LoginRequestDto;
import com.br.desafio.votacao.service.AssociadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("associados")
@RequiredArgsConstructor
@Api(value = "Associado API")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;

	@ApiOperation(value = "Cadastrar Um Associado")
	@PostMapping
    public ResponseEntity<AssociadoDto> cadastra(@Valid @RequestBody AssociadoDto associadoDto ){
		associadoService.save(associadoDto);
        return ResponseEntity.ok(associadoDto);
    }
	@ApiOperation(value = "Logar Um Associado")
	@PostMapping("/login")
	public ResponseEntity<LoginDto> login(@RequestBody LoginRequestDto dto ){
        return ResponseEntity.ok(associadoService.login(dto.getCpf()));
	}

}
