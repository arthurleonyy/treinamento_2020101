package com.indracompany.treinamento.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SaqueDTO extends GenericDTO {		
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667293515423048229L;
	
	private String agencia;
	private String numeroConta;	
	private double valor;
}
