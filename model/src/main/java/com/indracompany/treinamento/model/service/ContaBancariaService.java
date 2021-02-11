package com.indracompany.treinamento.model.service;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ExtratoBancarioService extratoBancarioService;
	
	private boolean transferencia = false;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime now;
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		extratoBancarioService.atualizarExtrato("Realizou uma busca por contas cadastradas no cpf", 0,null, null, cli, null);
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		transferencia = true;
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());
		now = LocalDateTime.now();
		
		extratoBancarioService.atualizarExtrato("Realizou uma transferencia", dto.getValor(), 
									     consultarConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem()),
									     consultarConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino()), null, dtf.format(now));
		transferencia = false;
	}
	
	
	public void depositar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);		
		conta.setSaldo(conta.getSaldo() + valor);
		if(!transferencia) {
			now = LocalDateTime.now();
			extratoBancarioService.atualizarExtrato("Realizou um deposito", valor, conta, null, null, dtf.format(now));
		}
		super.salvar(conta);
		
		
	}
		
	public void sacar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = consultarConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		if(!transferencia) {
			now = LocalDateTime.now();
			extratoBancarioService.atualizarExtrato("Realizou um saque", valor, conta,  null, null, dtf.format(now));
		}
		super.salvar(conta);
	}
	
	public double consultarSaldo(String agencia, String numeroConta) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		now = LocalDateTime.now();
		extratoBancarioService.atualizarExtrato("Realizou uma consulta do saldo", conta.getSaldo(), conta,  null, null, dtf.format(now));
		return conta.getSaldo();
	}
	
	
	public ContaBancaria consultarConta(String agencia, String numeroConta) {
		ContaBancaria conta = repository.findByAgenciaAndNumero(agencia, numeroConta);
		if(conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		return conta;
	}
			
}
