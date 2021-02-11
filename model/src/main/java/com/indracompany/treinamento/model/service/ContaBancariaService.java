package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ExtratoBancarioService extratoBancario;
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());
		//extratoBancario.atualizarExtrato("Fez uma transferencia para a conta referente a agencia " + dto.getAgenciaDestino() + "", dto.getValor(), , null);
	}
	
	
	public void depositar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);		
		conta.setSaldo(conta.getSaldo() + valor);
		extratoBancario.atualizarExtrato("Fez um deposito", valor, conta, null);		
		super.salvar(conta);
		
		
	}
		
	public void sacar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = consultarConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		extratoBancario.atualizarExtrato("Fez um saque", valor, conta, null);
		super.salvar(conta);
	}
	
	public double consultarSaldo(String agencia, String numeroConta) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		extratoBancario.atualizarExtrato("consultou saldo", conta.getSaldo(), conta, null);
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
