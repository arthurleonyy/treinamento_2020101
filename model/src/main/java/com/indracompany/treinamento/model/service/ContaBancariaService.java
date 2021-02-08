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
import com.indracompany.treinamento.model.entity.enums.TransacaoExtratoEnum;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TransacaoExtratoService tes;

	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		TransacaoExtratoEnum trasacao = TransacaoExtratoEnum.TRANSFERENCIA;
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());
		ContaBancaria contaDestino = this.consultaConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino());
		tes.geraExtrato(contaDestino, dto.getValor(), trasacao);
	}

	public void depositar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		TransacaoExtratoEnum trasacao = TransacaoExtratoEnum.DEPOSITO;
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		tes.geraExtrato(conta, valor, trasacao);
	}

	public void sacar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		TransacaoExtratoEnum trasacao = TransacaoExtratoEnum.SAQUE;
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		tes.geraExtrato(conta, (-valor), trasacao);
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