package com.br.desafio.votacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.desafio.votacao.domain.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
	
	List<Votacao> findByPautaId(Long pautaId);
	List<Votacao> findByAssociadoIdAndPautaId(Long associadoId, Long pautaId);

}
