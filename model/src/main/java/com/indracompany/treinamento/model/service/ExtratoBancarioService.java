package com.indracompany.treinamento.model.service;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;

@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository >{	
	
	public void atualizarExtrato (String relatorio, double valor, ContaBancaria contaOrigem, ContaBancaria contaDestino, Cliente cliente, String data) {	
		ExtratoBancario extrato = new ExtratoBancario(relatorio,valor,contaOrigem,contaDestino,cliente,data);				
		super.salvar(extrato);			
	}
		
	
	
	
}
