package com.indracompany.treinamento.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTransacaoEnum {

	SAQUE("SAQUE"), DEPOSITO("DEPOSITO"), TRANSFERENCIA("TRANSFERENCIA");

	private String tipo;

}