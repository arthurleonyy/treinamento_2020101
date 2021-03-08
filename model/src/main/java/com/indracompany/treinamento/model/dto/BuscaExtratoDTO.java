package com.indracompany.treinamento.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BuscaExtratoDTO {

	private String agencia;
	private String conta;
	@JsonFormat(pattern="ddMMyyyy")
	@DateTimeFormat(pattern = "ddMMyyyy")
	private Date dataInicio;
	@JsonFormat(pattern="ddMMyyyy")
	@DateTimeFormat(pattern = "ddMMyyyy")
	private Date dataFim;
}
