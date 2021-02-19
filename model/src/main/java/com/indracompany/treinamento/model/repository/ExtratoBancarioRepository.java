package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario, Long>{
	
	public List<ExtratoBancario> findByAgenciaAndConta(String agencia, String conta);
	
	
	@Query(nativeQuery = true, value =  "SELECT * FROM extratos WHERE agencia = ?1 AND conta = ?2 AND data >= ?3 AND data <= ?4")
	public List<ExtratoBancario> ExtratoPorPeriodo(String agencia, String conta, Date datai, Date dataf);

	
	

}
