package com.br.desafio.votacao.exception;

import lombok.Data;

@Data
public class ErrorMessage {
	private int statusCode;
	private String mensagem;
	private String descricao;

	public ErrorMessage(int statusCode, String mensagem, String descricao) {
	    this.statusCode = statusCode;
	    this.mensagem = mensagem;
	    this.descricao = descricao;
	  }
}