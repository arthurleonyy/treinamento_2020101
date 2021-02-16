package com.indracompany.treinamento.model.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.Extrato;

public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long>{
	
	public List<Extrato> findByAgenciaAndConta(String agencia, String conta);
	
	
	@Query(nativeQuery = true, value =  "SELECT * FROM extratos WHERE agencia = ?1 AND conta = ?2 AND data >= ?3 AND data <= ?4")
	public List<Extrato> ExtratoData(String agencia, String conta, Date data1, Date data2);

	
	

}
