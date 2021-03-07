package com.indracompany.treinamento.model.dto;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExtratoBancarioDTO extends GenericDTO{

	private static final long serialVersionUID = 4320607655918916745L;
	
	private String agencia;
	private String conta;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	
	

}
