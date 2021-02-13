package com.indracompany.treinamento.model.service;

import org.springframework.stereotype.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.TransferenciaBancarioDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ExtratoService extratoService;
	
	boolean Transferencia = false;


	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancarioDTO dto) {
		
		Transferencia = true;
		
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());
		
		extratoService.salvarAcao(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), "Transferencia no valor de " + dto.getValor() + " para "
				+ "a Agencia destino: " + dto.getAgenciaDestino());
	
		extratoService.salvarAcao(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), "Transferencia recebida no valor de " + dto.getValor() + " da Agencia "
				+ "Origem: " + dto.getAgenciaOrigem());
		
		Transferencia = false;
		
		
	}

	public void depositar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		
		if(!Transferencia) {
			extratoService.salvarAcao(conta.getAgencia(), conta.getNumero(), "Deposito no valor de " + valor);
		}
		
	}

	public void sacar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		
		if(!Transferencia) {
			extratoService.salvarAcao(conta.getAgencia(), conta.getNumero(), "Saque no valor de " + valor);
		}
	}

	public double consultarSaldo(String agencia, String numeroConta) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		return conta.getSaldo();
	}

	public ContaBancaria consultaConta(String agencia, String numeroConta) {
		ContaBancaria conta = repository.findByAgenciaAndNumero(agencia, numeroConta);
		if (conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		return conta;
	}
	
	

}
