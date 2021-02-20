package com.indracompany.treinamento.model.service;

import java.text.DateFormat;
import java.util.Date;
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
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ExtratoBancarioService extratoService;

	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancarioDTO dto) {
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());

		ExtratoBancario extrato = new ExtratoBancario();
		extrato.setOperacao("1");
		Date data = new Date();
		extrato.setData(java.text.DateFormat.getDateInstance(DateFormat.SHORT).format(data) + " " + data.getHours()
		+ ":" + data.getMinutes());
		extratoService.salvar(extrato);
	}

	public void depositar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		
		
		ExtratoBancario extrato = new ExtratoBancario();
		extrato.setAgencia(agencia);
		extrato.setNumero(numeroConta);
		extrato.setOperacao("2");
		extrato.setSaldo(conta.getSaldo());
		extrato.setValor(valor);
		extrato.setCliente(conta.getCliente());
		Date data = new Date();
		extrato.setData(java.text.DateFormat.getDateInstance(DateFormat.SHORT).format(data) + " " + data.getHours()
		+ ":" + data.getMinutes());

		extratoService.salvar(extrato);
		super.salvar(conta);

	}

	public void sacar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);


		ExtratoBancario extrato = new ExtratoBancario();
		extrato.setAgencia(agencia);
		extrato.setNumero(numeroConta);
		extrato.setOperacao("3");
		extrato.setSaldo(conta.getSaldo());
		extrato.setValor(valor);
		extrato.setCliente(conta.getCliente());
		Date data = new Date();
		extrato.setData(java.text.DateFormat.getDateInstance(DateFormat.SHORT).format(data) + " " + data.getHours()
		+ ":" + data.getMinutes());


		extratoService.salvar(extrato);
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


		ExtratoBancario extrato = new ExtratoBancario();
		extrato.setAgencia(agencia);
		extrato.setNumero(numeroConta);
		extrato.setOperacao("2");
		extrato.setSaldo(conta.getSaldo());
		extrato.setCliente(conta.getCliente());
		Date data = new Date();
		extrato.setData(java.text.DateFormat.getDateInstance(DateFormat.SHORT).format(data) + " " + data.getHours()
		+ ":" + data.getMinutes());


		extratoService.salvar(extrato);
		return conta;
	}

}
