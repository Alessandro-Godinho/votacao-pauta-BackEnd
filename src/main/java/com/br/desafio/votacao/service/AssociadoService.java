package com.br.desafio.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.dto.AssociadoDto;
import com.br.desafio.votacao.dto.LoginDto;
import com.br.desafio.votacao.exception.RegistroNaoEncontradoException;
import com.br.desafio.votacao.exception.ValidacaoDeRegraDeNegocioException;
import com.br.desafio.votacao.repository.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository associadoRepository;

	public Associado save(AssociadoDto associadoDto)  {
		
		if(associadoRepository.findByCpf(associadoDto.getCpf()).isPresent())
			throw new ValidacaoDeRegraDeNegocioException("Esse CPF já está cadastrado");


		Associado associado = convertToModel(associadoDto);
		return associadoRepository.save(associado);
	}
	

	private Associado convertToModel(AssociadoDto associadoDto) {
		
		return Associado.builder()
				.cpf(associadoDto.getCpf())
				.nome(associadoDto.getNome())
				.build();
	}


	public LoginDto login(String cpf) {
		
		var associado = associadoRepository.findByCpf(cpf);
		if(!associado.isPresent()) {
			associado.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado esse associado"));
		}
		return LoginDto.builder()
				.id(associado.get().getId())
				.nome(associado.get().getNome())
				.build();
	}

}
