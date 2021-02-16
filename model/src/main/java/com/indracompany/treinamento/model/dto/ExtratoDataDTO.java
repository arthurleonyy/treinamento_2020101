package com.indracompany.treinamento.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExtratoDataDTO extends GenericDTO{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String agencia;
	private String conta;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date data1;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date data2;
	
	

}
