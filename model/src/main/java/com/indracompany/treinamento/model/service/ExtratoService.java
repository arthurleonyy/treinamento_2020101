package com.indracompany.treinamento.model.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{
	
	public void salvarAcao(String agencia, String conta, String acao) {
		
		Extrato extrato = new Extrato();
			
		extrato.setAcao(acao);
		extrato.setAgencia(agencia);
		extrato.setConta(conta);
		extrato.setData(java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date()));
		
		
		super.salvar(extrato);
		
	}
	
	public List<Extrato> gerarExtrato(String agencia, String conta) {
		
		List<Extrato> acoes = repository.findByAgenciaAndConta(agencia, conta);
		if (acoes == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return acoes;
		
		
	}
	
	

}
