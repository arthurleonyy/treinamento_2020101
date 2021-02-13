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
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.Constantes;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ExtratoBancarioService extrato;
	
	private Boolean transferencia = false; 
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancarioDTO dto) {
		transferencia = true;
		
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor());
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor());
		
		ContaBancaria contaBancariaPrimaria = this.consultaConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino());
		ContaBancaria contaBancariaSecundaria = this.consultaConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem());
		
		extrato.geraExtrato(contaBancariaPrimaria,  dto.getValor(), Constantes.TRANSACAO_TRANSFERENECIA);
		extrato.geraExtrato(contaBancariaSecundaria, - dto.getValor(), Constantes.TRANSACAO_TRANSFERENECIA);
		
		transferencia = false;
		
	}
	
	public void depositar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		
			if(!transferencia){
				extrato.geraExtrato(conta, valor, Constantes.TRANSACAO_DEPOSITO);
			}
		
		}
	
	public void sacar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		
			if(!transferencia){
				extrato.geraExtrato(conta, -valor, Constantes.TRANSACAO_SAQUE);
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
