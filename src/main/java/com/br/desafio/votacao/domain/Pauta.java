package com.br.desafio.votacao.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pauta")
@Entity

public class Pauta {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private String descricao;
    @Column(columnDefinition = "DATETIME")
	private LocalDateTime dataInicio;
    @Column(columnDefinition = "DATETIME")
	private LocalDateTime dataFim;
	@OneToMany(mappedBy = "pauta", fetch=FetchType.EAGER)
	private List<Votacao> votacao;

}
