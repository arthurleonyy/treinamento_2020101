package com.indracompany.treinamento.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;

public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long>{
	
	public List<Extrato> findByAgenciaAndConta(String agencia, String conta);

}
