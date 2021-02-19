package com.indracompany.treinamento.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;

@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository>{
	
	public void salvarAcao(String agencia, String conta, String acao) {
		
		ExtratoBancario extrato = new ExtratoBancario();
		
		extrato.setAcao(acao);
		extrato.setAgencia(agencia);
		extrato.setConta(conta);
		extrato.setData(new Date());
		
		super.salvar(extrato);
		
	
		
	}
	
public List<ExtratoBancario> criarExtratoPorPeriodo(String agencia, String conta, Date datai, Date dataf) {
	
		List<ExtratoBancario> acoes = repository.ExtratoPorPeriodo(agencia, conta, datai, dataf);

		if (acoes == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return acoes;
		
	}
	
	

}
