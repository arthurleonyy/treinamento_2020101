package com.indracompany.treinamento.model.service;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.contaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<contaBancaria, Long, ContaBancariaRepository> {
	
	public ContaBancaria consultaConta(String agencia, String numeroConta) {
		
	
		
	}
	

}
