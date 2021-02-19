package com.indracompany.treinamento.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Extrato extends GenericDTO{


	private static final long serialVersionUID = 1L;
	
	private String agencia;
	private String conta;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date datai;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataf;
	
}
