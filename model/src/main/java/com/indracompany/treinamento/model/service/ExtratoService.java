package com.indracompany.treinamento.model.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ExtratoService extends GenericCrudService<ExtratoBancario, Long, ExtratoRepository> {
	
	
	public List<ExtratoBancario> emitirExtrato(String agencia, String conta){
		List<ExtratoBancario> extrato = repository.findByAgenciaAndConta(agencia, conta);
		
		return extrato;
	}

	public List<ExtratoBancario> emitirExtratoComData(String agencia, String conta, String dataInicio, String dataFim){
		
		Date dataA = Date.valueOf(dataInicio);
		Date dataB = Date.valueOf(dataFim);
		
		//List<ExtratoBancario> extrato = repository.findByAgenciaAndContaAndDataAfterAndDataBefore(agencia, conta, dataA, dataB);
		
		List<ExtratoBancario> extrato = repository.findByAgenciaAndContaAndDataBetween(agencia, conta, dataA, dataB);
		
		return extrato;
	}
	
}
