package com.indracompany.treinamento.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransferenciaBancariaDTO extends GenericDTO {
	
	private static final long serialVersionUID = -4146773220700171235L;
	
	private String agenciaOrigem;
	private String numeroContaOrigem;
	private String agenciaDestino;
	private String numeroContaDestino;
	private double valor;
}
