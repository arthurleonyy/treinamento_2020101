package com.indracompany.treinamento.model.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		this.sacar(dto.getAgenciaOrigem(),dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(),dto.getNumeroContaDestino(), dto.getValor());
		
	}
	
	public void depositar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		
	}
	
	public void sacar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERROR_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
	}
	
	public double consultarSaldo (String agencia, String numeroConta) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		return conta.getSaldo();
		
	}

	public ContaBancaria consultarConta (String agencia, String numeroConta) {
		ContaBancaria conta = repository.findByAgenciaAndNumero(agencia, numeroConta);
		if(conta ==null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		return conta;
	}
	
}
