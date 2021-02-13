package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.Extrato;

@Repository
public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long> {

	@Query("select e from Extrato e where e.data LIKE CONCAT('%',:mesAnoFormatado,'%')")
	//@Query("SELECT e.data , e.tipoTransacao, e.valorTrasacao, e.saldoFinalTrasacao , c.agencia, c.numero FROM Extrato e INNER JOIN ContaBancaria c ON e.contaBancaria = c.id WHERE e.data LIKE CONCAT('%',:mesAnoFormatado,'%')")
	public List<Object> buscarExtratoMesAno(@Param("mesAnoFormatado")String mesAno);
	
}
