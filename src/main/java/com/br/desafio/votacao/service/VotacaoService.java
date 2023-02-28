package com.br.desafio.votacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.desafio.votacao.domain.Associado;
import com.br.desafio.votacao.domain.Pauta;
import com.br.desafio.votacao.domain.Votacao;
import com.br.desafio.votacao.dto.ResultadoVotacaoDto;
import com.br.desafio.votacao.dto.VotosDto;
import com.br.desafio.votacao.exception.RegistroNaoEncontradoException;
import com.br.desafio.votacao.exception.RegistroNaoProcessadoException;
import com.br.desafio.votacao.exception.ValidacaoDeRegraDeNegocioException;
import com.br.desafio.votacao.repository.AssociadoRepository;
import com.br.desafio.votacao.repository.PautaRepository;
import com.br.desafio.votacao.repository.VotacaoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotacaoService {

	@Autowired
	private PautaRepository pautaRepository;
	@Autowired
	private VotacaoRepository votacaoRepository;
	@Autowired
	private AssociadoRepository associadoRepository;

	//verifica se o associado já votou na pauta. senão, faz a votação
	public void votar(VotosDto votoDto) {

		log.info("verificando se existe a pauta " + votoDto.getIdPauta());
		Pauta pauta = pautaRepository.findById(votoDto.getIdPauta())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado essa pauta"));
		
		log.info("verificando se existe o associado " + votoDto.getIdAssociado());
		Associado associado = associadoRepository.findById(votoDto.getIdAssociado())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Não foi encontrado esse associado"));

		var  associadoVotos = votacaoRepository.findByAssociadoIdAndPautaId(votoDto.getIdAssociado(), votoDto.getIdPauta());

		Boolean existeVotoAssociado =  associadoVotos.stream().filter(x->x.getAssociado().getId() == votoDto.getIdAssociado())
		.filter(y->y.getPauta().getId() == votoDto.getIdPauta())
		.findAny().isPresent();
		
		if (existeVotoAssociado) {

			throw new ValidacaoDeRegraDeNegocioException("Essa pauta já foi votada por esse associado");
		}
		
		try {

			votacaoRepository.save(Votacao.builder()
					.associado(associado)
					.pauta(pauta)
					.voto(votoDto.getVoto()).build());

		} catch (Exception e) {
			throw new RegistroNaoProcessadoException("Voto não registrado");
		}
		
		log.info("votação salva com sucesso");

	}

	public ResultadoVotacaoDto resultado(Long idPauta) {
		
		List<Votacao> votos = votacaoRepository.findByPautaId(idPauta);

		if(votos.isEmpty()) {
			throw new RegistroNaoEncontradoException("Voto não registrado");
		}
		
		return ResultadoVotacaoDto.builder()
		.totalSim(votos.stream().filter(x->x.getVoto()).count())
		.totalNao(votos.stream().filter(x->!x.getVoto()).count())
		.totalVotos(votos.stream().map(Votacao::getVoto).count())
		.build();
	}

}
