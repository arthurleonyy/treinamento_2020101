package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.Extrato;

@Repository
public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long>{
	
	@Query("SELECT e FROM Extrato e WHERE e.agencia = :agencia AND e.numero = :numero AND e.data_transacao LIKE CONCAT(:data,'%%')")
	public List<Extrato> buscarExtrato(@Param("agencia") String agencia, @Param("numero") String numero, @Param("data") String data);
	
}
