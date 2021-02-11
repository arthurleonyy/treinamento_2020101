package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario, Long> {
		
	public List<ExtratoBancario> findByContaBancariaOrigem(ContaBancaria conta);
	
	public List<ExtratoBancario> findByContaBancariaDestino(ContaBancaria conta);
		
}
