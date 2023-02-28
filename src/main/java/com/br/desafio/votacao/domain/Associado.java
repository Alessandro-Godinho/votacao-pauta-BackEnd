package com.br.desafio.votacao.domain;

import java.util.List;

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
@Entity
@Table(name = "associado")

public class Associado {


	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String nome;
	private String cpf;
	@OneToMany(mappedBy = "associado", fetch=FetchType.EAGER)
	private List<Votacao> votacao;;

}
