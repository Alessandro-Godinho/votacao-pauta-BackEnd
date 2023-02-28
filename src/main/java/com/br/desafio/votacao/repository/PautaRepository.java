package com.br.desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.desafio.votacao.domain.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
