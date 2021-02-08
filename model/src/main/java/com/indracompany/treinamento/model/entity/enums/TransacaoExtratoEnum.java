package com.indracompany.treinamento.model.entity.enums;

public enum TransacaoExtratoEnum {

	SAQUE("SAQUE"), DEPOSITO("DEPOSITO"), TRANSFERENCIA("TRANSFERENCIA");

	private String descricao;

	TransacaoExtratoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
