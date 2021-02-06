package com.indracompany.treinamento.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransferenciaBancariaDTO extends GenericDTO {
	
	
	
	private String agenciaOrigem;
	private String numeroContaOrigem;
	private String agenciaDestino;
	private String numeroContaDestino;
	private double valor;
}
