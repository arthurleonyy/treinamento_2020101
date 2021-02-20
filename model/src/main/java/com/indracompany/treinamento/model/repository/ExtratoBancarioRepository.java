package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario, Long> {
	
	public List<ExtratoBancario> findByAgenciaAndNumero(String agencia, String conta);
	
}

