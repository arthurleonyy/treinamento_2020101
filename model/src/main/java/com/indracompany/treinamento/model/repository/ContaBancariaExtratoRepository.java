package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ContaBancariaExtratoRepository extends GenericCrudRepository<ExtratoBancario, Long>{

	@Query(nativeQuery = true, value =  "SELECT * FROM extratosBancario WHERE agencia = ?1 AND "
			+ "conta = ?2 AND inicio >= ?3 AND fim <= ?4")
	
	public List<ExtratoBancario> findByAgenciaAndConta(String agencia, String conta, Date inicio, Date fim);

	public List<ExtratoBancario> ExtratoData(String agencia, String conta, Date inicio, Date fim);
			
}
