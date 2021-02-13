package com.indracompany.treinamento.model.repository;

import java.sql.Date;
import java.util.List;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ExtratoRepository extends GenericCrudRepository<ExtratoBancario, Long> {
	
	public List<ExtratoBancario> findByAgenciaAndConta(String agencia, String conta);
	
	//public List<ExtratoBancario> findByAgenciaAndContaAndDataAfterAndDataBefore(String agencia, String numero, Date dataInicio, Date dataFim);

	public List<ExtratoBancario> findByAgenciaAndContaAndDataBetween(String agencia, String numero, Date dataInicio, Date dataFim);
	
}
