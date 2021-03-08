package com.indracompany.treinamento.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExtratoDTO {

	private String conta;
	private String agencia;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;
}
