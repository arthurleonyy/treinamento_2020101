package com.indracompany.treinamento.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	
	Date data = new Date();
	SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	
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
		ContaBancaria contaDestino = this.consultaConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino());
		fmt.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
		String dataView = fmt.format(data);
		
		ExtratoBancario extOrigem = new ExtratoBancario();
		extOrigem.setAgencia(dto.getAgenciaOrigem());
		extOrigem.setConta(dto.getNumeroContaOrigem());
		extOrigem.setCliente(conta.getCliente());
		extOrigem.setOperacao("transferencia");
		extOrigem.setValor(dto.getValor());
		extOrigem.setSaldo(conta.getSaldo());
		extOrigem.setContaOrigem(dto.getAgenciaOrigem() + " " + dto.getNumeroContaOrigem());
		extOrigem.setContaDestino(dto.getAgenciaDestino() + " " + dto.getNumeroContaDestino());
		extOrigem.setDataView(dataView);
		extrato.salvar(extOrigem);
		
		ExtratoBancario extDestino = new ExtratoBancario();
		extDestino.setAgencia(dto.getAgenciaDestino());
		extDestino.setConta(dto.getNumeroContaDestino());
		extDestino.setCliente(contaDestino.getCliente());
		extDestino.setOperacao("transferencia");
		extDestino.setValor(dto.getValor());
		extDestino.setSaldo(contaDestino.getSaldo());
		extDestino.setContaOrigem(dto.getAgenciaOrigem() + " " + dto.getNumeroContaOrigem());
		extDestino.setContaDestino(dto.getAgenciaDestino() + " " + dto.getNumeroContaDestino());
		extDestino.setDataView(dataView);
		extrato.salvar(extDestino);
	}
	
	
	public void depositar (String agencia, String numeroConta, double valor, boolean ehTransf) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		
		if(!ehTransf) {
			fmt.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
			String dataView = fmt.format(data);
			ExtratoBancario ext = new ExtratoBancario();
			ext.setAgencia(agencia);
			ext.setConta(numeroConta);
			ext.setCliente(conta.getCliente());
			ext.setOperacao("deposito");
			ext.setValor(valor);
			ext.setSaldo(conta.getSaldo());
			ext.setDataView(dataView);
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
		
		
		if(!ehTransf) {
			fmt.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
			String dataView = fmt.format(data);
			ExtratoBancario ext = new ExtratoBancario();
			ext.setAgencia(agencia);
			ext.setConta(numeroConta);
			ext.setCliente(conta.getCliente());
			ext.setOperacao("saque");
			ext.setValor(valor);
			ext.setSaldo(conta.getSaldo());
			ext.setDataView(dataView);
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
