package br.com.davsantos.productapi.config.exceptions;

import lombok.Data;

@Data
public class ExceptionDetail {

	private int status;
	private String message;
}
