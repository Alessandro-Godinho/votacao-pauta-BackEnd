package com.br.desafio.votacao.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.dto.ExpiracaoPautaDto;
import com.br.desafio.votacao.dto.PautaDto;
import com.br.desafio.votacao.dto.PautaResponseDto;
import com.br.desafio.votacao.exception.RegistroNaoEncontradoException;
import com.br.desafio.votacao.exception.RegistroNaoProcessadoException;
import com.br.desafio.votacao.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta save(PautaDto pautaDto) {
		
		try {
			Pauta pauta = convertToModel(pautaDto);
			return pautaRepository.save(pauta);
			
		} catch (Exception ex) {
			throw new RegistroNaoProcessadoException("Pauta não registrada");
		}

	}

	private Pauta convertToModel(PautaDto pautaDto) {
		return Pauta.builder()
				.dataFim(convertToDateTime(pautaDto.getDataFim()))
				.dataInicio(convertToDateTime(pautaDto.getDataInicio()))
				.descricao(pautaDto.getDescricao())
				.titulo(pautaDto.getTitulo())
				.build();
	}


	public List<PautaDto> getAll() {
		
		List<Pauta> list = pautaRepository.findAll();

		return list.stream().map(x -> modelToDto(x)).collect(Collectors.toList());

	}

	private PautaDto modelToDto(Pauta x) {
		return PautaDto.builder()
				.id(x.getId())
				.dataInicio(x.getDataInicio().toString())
				.dataFim(x.getDataFim().toString())
				.descricao(x.getDescricao())
				.titulo(x.getTitulo())
				.build();
	}

	public ExpiracaoPautaDto verificaExpiracao(Long id, String data) {
		teste();
		Pauta pauta = findById(id);
		LocalDateTime dataConvertida = convertToDateTime(data);
		if(dataConvertida.isAfter(pauta.getDataFim())) {
			return ExpiracaoPautaDto.builder()
					.tempoExpirado(true)
					.minutosRestante(0l)
					.build();
		}
		Duration duration = Duration.between(dataConvertida, pauta.getDataFim());
		
		return ExpiracaoPautaDto.builder()
				.tempoExpirado(false)
				.minutosRestante(duration.toMinutes())
				.build();	
		}

	private void teste() {
		 LocalDateTime firstDateTime = LocalDateTime.of(2022, 01, 25, 12, 55, 10);
	     LocalDateTime secondDateTime = LocalDateTime.of(2022, 01, 25, 12, 50, 10);

	        if (firstDateTime.isBefore(secondDateTime)) {
	            System.out.println(firstDateTime + " é antes de " + secondDateTime);
	        } else if (firstDateTime.isAfter(secondDateTime)) {
	            System.out.println(firstDateTime + " é depois de " + secondDateTime);
	        } else {
	            System.out.println(firstDateTime + " é igual a " + secondDateTime);
	        }		
	}

	private LocalDateTime convertToDateTime(String data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	
		return LocalDateTime.parse(data, formatter);
	}

	public PautaResponseDto obterPautaporId(Long id) {
		Pauta pauta = findById(id);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return PautaResponseDto.builder()
				.id(pauta.getId())
				.titulo(pauta.getTitulo())
				.descricao(pauta.getDescricao())
	            .dataInicio(pauta.getDataInicio().format(formatter))
				.build();
	}

	private Pauta findById(Long id) {
		return pautaRepository.findById(id)
				.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado essa pauta"));
	}


}
