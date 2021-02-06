package com.indracompany.treinamento.model.dto;

import lombok.Data;

@Data
public class TransacaoDTO extends GenericDTO {

	private static final long serialVersionUID = -1730994793188389780L;
	
	private String agencia;
	private String conta;
	private Double valor;
	

}
