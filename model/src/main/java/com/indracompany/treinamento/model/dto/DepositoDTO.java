package com.indracompany.treinamento.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DepositoDTO extends GenericDTO {		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2985703489738688613L;
	
	private String agencia;
	private String numeroConta;	
	private double valor;
}
