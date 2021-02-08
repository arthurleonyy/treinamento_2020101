package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.RegistroTransacoes;

public interface RegistroTransacoesRepository extends GenericCrudRepository<RegistroTransacoes,Long> {

	@Query("select new RegistroTransacoes(r.id, r.tipoTransacao, r.descricao, r.data, r.valor) "
				+ "from RegistroTransacoes r "
					+ "where r.conta.agencia = :agencia and "
					+ "r.conta.numero = :numeroConta and "
					+ "r.data >= :dataInicio and r.data <= :dataFim")
	public List<RegistroTransacoes> getExtrato(@Param("agencia") String agencia, 
			@Param("numeroConta") String numeroConta, 
			@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
	
}
