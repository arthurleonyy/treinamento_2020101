package com.indracompany.treinamento.model.dto;

import lombok.Data;

@Data
public class TransferenciaBancariaDTO extends GenericDTO {
	
	private String agenciaOrigem;
	private String numeroContaOrigem;
	private String agenciaDestino;
	private String numeroContaDestino;
	private double valor;
	

}
