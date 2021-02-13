package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.TransferenciaBancarioDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ExtratoService extrato;
	
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.findByCliente(cli);
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancarioDTO dto) {
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor(), true);
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor(), true);
		
		ContaBancaria conta = this.consultaConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem());
		
		ExtratoBancario ext = new ExtratoBancario();
		ext.setAgencia(dto.getAgenciaOrigem());
		ext.setConta(dto.getNumeroContaOrigem());
		ext.setCliente(conta.getCliente());
		ext.setOperacao("transferencia");
		ext.setValor(dto.getValor());
		ext.setSaldo(conta.getSaldo());
		ext.setContaOrigem(dto.getAgenciaOrigem() + " " + dto.getNumeroContaOrigem());
		ext.setContaDestino(dto.getAgenciaDestino() + " " + dto.getNumeroContaDestino());
		extrato.salvar(ext);
	}
	
	
	public void depositar (String agencia, String numeroConta, double valor, boolean ehTransf) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		
		if (!ehTransf) {
			ExtratoBancario ext = new ExtratoBancario();
			ext.setAgencia(agencia);
			ext.setConta(numeroConta);
			ext.setCliente(conta.getCliente());
			ext.setOperacao("deposito");
			ext.setValor(valor);
			ext.setSaldo(conta.getSaldo());
			extrato.salvar(ext);
		}
		
		
		super.salvar(conta);
	}
	
	public void sacar (String agencia, String numeroConta, double valor, boolean ehTransf) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		
		if (!ehTransf) {
			ExtratoBancario ext = new ExtratoBancario();
			ext.setAgencia(agencia);
			ext.setConta(numeroConta);
			ext.setCliente(conta.getCliente());
			ext.setOperacao("saque");
			ext.setValor(valor);
			ext.setSaldo(conta.getSaldo());
			extrato.salvar(ext);
		}
		
		super.salvar(conta);
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
