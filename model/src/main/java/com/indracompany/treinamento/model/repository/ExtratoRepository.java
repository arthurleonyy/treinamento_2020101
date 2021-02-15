package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Extrato;
import java.util.Date;


public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long> {
	
	// public Extrato findByExtratoId(String agencia, String conta);
	// @Query(value="select * from extrato WHERE fk_conta_bancaria_id = :conta_bancaria_id or fk_conta_origem_id = :conta_bancaria_id",nativeQuery=true)
	Extrato findByContaId(Long id);
	// public Extrato registrar);
	
	// public Extrato createExtrato(Long contaDestinoId, Date data_transacao, double valor, String tipo_transacao, Long contaOrigemId);

}
