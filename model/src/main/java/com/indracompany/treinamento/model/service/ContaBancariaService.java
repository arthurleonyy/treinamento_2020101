package com.indracompany.treinamento.model.service;

import java.time.LocalDate;
import java.util.List;

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
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ExtratoBancarioService extratoBancarioService;

	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		String descricaoSaque = "TRANSFERENCIA PARA AG: "+dto.getAgenciaDestino() +" CONTA: "+dto.getNumeroContaDestino();
		String descricaoDeposito = "TRANSFERENCIA DE AG: "+dto.getAgenciaOrigem() +" CONTA: "+dto.getNumeroContaOrigem();;
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor(), descricaoSaque);
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor(), descricaoDeposito);
	}

	public void depositar (String agencia, String numeroConta, double valor, String descricao) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		
		extratoBancarioService.registarOperacaoExtrato(conta, descricao == null ? "DEPOSITO" : descricao, valor, 'C',
				LocalDate.now());
	}

	public void sacar (String agencia, String numeroConta, double valor, String descricao) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		
		extratoBancarioService.registarOperacaoExtrato(conta, descricao == null ? "SAQUE" : descricao, valor, 'D',
				LocalDate.now());

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