package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;

@Service
public class ExtratoBancarioService  extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository>{

	public List<ExtratoBancario> gerarExtrato(String agencia, String conta ){
		List<ExtratoBancario> extrato = repository.findByAgenciaAndNumero(agencia, conta);
		
		return extrato;
		
	}
	
}
