package com.br.desafio.votacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.dto.ExpiracaoPautaDto;
import com.br.desafio.votacao.dto.PautaDto;
import com.br.desafio.votacao.dto.PautaResponseDto;
import com.br.desafio.votacao.service.PautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pautas")
@RequiredArgsConstructor
@Api(value = "Criação e Votação da Pauta API")
public class PautaController {
	
	private final PautaService pautaService;
	//private final AssociadoService associadoService;
	
	@ApiOperation(value = "Criar Uma Pauta")
	@PostMapping
    public ResponseEntity<PautaDto> cadastra(@Valid @RequestBody PautaDto pautaDto ){
		pautaService.save(pautaDto);
        return ResponseEntity.ok(pautaDto);
    }
	
	@ApiOperation(value = "Obter uma Pauta")
	@GetMapping("/{id}")
    public ResponseEntity<PautaResponseDto> obterPautaporId(@PathVariable Long id){
        return ResponseEntity.ok(pautaService.obterPautaporId(id));

    }
	
	@GetMapping
	@ApiOperation(value = "Buscar Todas as Pautas")
    public ResponseEntity<List<PautaDto>> getAll(){
        List<PautaDto> list = pautaService.getAll();
        return ResponseEntity.ok(list);
    }
	
	@ApiOperation(value = "Verifica expiração da pauta para votação")
	@GetMapping("/{id}/expiracao")
    public ResponseEntity<ExpiracaoPautaDto> verificaExpiracao(@PathVariable Long id, @RequestParam String data){
        return ResponseEntity.ok(pautaService.verificaExpiracao(id, data));

    }
	

}
