package com.br.desafio.votacao.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votacao")

public class Votacao {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private Boolean voto;

	@ManyToOne
	@JoinColumn(name = "associado_id", referencedColumnName = "id")
    private Associado associado;
	@ManyToOne
	@JoinColumn(name = "pauta_id", referencedColumnName = "id")
	private Pauta pauta;

}
