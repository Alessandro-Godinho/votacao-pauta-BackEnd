package com.br.desafio.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
	

	
	@ExceptionHandler(ValidacaoDeRegraDeNegocioException.class)
	public ResponseEntity<ErrorMessage> validacaoDeRegraDeNegocio(ValidacaoDeRegraDeNegocioException ex, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(
		        HttpStatus.CONFLICT.value(),
		        ex.getMessage(),
		        request.getDescription(false));
		    
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> registroNaoEncontradoException(RegistroNaoEncontradoException ex, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(
		        HttpStatus.NOT_FOUND.value(),
		        ex.getMessage(),
		        request.getDescription(false));
		    
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RegistroNaoProcessadoException.class)
	public ResponseEntity<ErrorMessage> registroNaoProcessadoException(RegistroNaoProcessadoException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(
		        HttpStatus.UNPROCESSABLE_ENTITY.value(),
		        ex.getMessage(),
		        request.getDescription(false));
		    
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
