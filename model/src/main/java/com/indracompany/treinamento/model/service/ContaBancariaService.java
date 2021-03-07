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
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.TiposTransacao;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private RegistroTransacoesService registroTransacoesService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		if(cli == null) 
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CLIENTE_INEXISTENTE);
		
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		
		if(contasDoCliente.isEmpty())
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_CPF_SEM_CONTA);
		
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		if(dto.getAgenciaOrigem().equals(dto.getAgenciaDestino()) && dto.getNumeroContaOrigem().equals(dto.getNumeroContaDestino()))
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTAS_IDENTICAS);
		
		ContaBancaria contaOrigem;
		
		try {
			contaOrigem = consultaConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem());
		}catch (AplicacaoException e) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_ORIGEM_TRANSFERENCIA_INVALIDA);
		}
		
		if (contaOrigem.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		
		contaOrigem.setSaldo(contaOrigem.getSaldo() - dto.getValor());
		
		ContaBancaria contaDestino;
		
		try {
			contaDestino = consultaConta(dto.getAgenciaDestino(),dto.getNumeroContaDestino());
		}catch (AplicacaoException e) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_DESTINO_TRANSFERENCIA_INVALIDA);
		}
		
		contaDestino.setSaldo(contaDestino.getSaldo() + dto.getValor());
		
		super.salvar(contaOrigem);
		super.salvar(contaDestino);
		
		registroTransacoesService.registraTransacao(contaOrigem, dto.getValor(), TiposTransacao.TRANSFERENCIA_ENVIADA,"Transferencia enviada");
		registroTransacoesService.registraTransacao(contaDestino, dto.getValor(), TiposTransacao.TRANSFERENCIA_RECEBIDA,"Transferencia recebida");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void depositar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		super.salvar(conta);
		registroTransacoesService.registraTransacao(conta, valor, TiposTransacao.DEPOSITO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void sacar (String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		super.salvar(conta);
		
		registroTransacoesService.registraTransacao(conta, valor, TiposTransacao.SAQUE);
	}
	
	public double consultarSaldo(String agencia, String numeroConta) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		return conta.getSaldo();
	}
	
	public ContaBancaria consultaConta(String agencia, String numeroConta) {
		ContaBancaria conta = repository.findByAgenciaAndNumero(agencia, numeroConta);
		if(conta == null) throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		return conta;
	}
}
