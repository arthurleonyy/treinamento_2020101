package com.indracompany.treinamento.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum TiposTransacao {
	DEPOSITO("D"),
	SAQUE("S"),
	TRANSFERENCIA_ENVIADA("TE"),
	TRANSFERENCIA_RECEBIDA("TR");
	@Setter
	@Getter
	private String tipo;
	
	public static TiposTransacao findByValue(String tipo){
	    for(TiposTransacao t : values()){
	        if( t.tipo.equals(tipo)){
	            return t;
	        }
	    }
	    return null;
	}

	
}
