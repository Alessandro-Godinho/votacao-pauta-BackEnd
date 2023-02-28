package com.br.desafio.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafio.votacao.domain.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
	Optional<Associado> findByCpf(String cpf);

}
