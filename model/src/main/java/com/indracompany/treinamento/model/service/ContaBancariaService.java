package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.GenericDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancarioDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.enums.TipoTransacaoEnum;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ExtratoService extratoService;
	
	
	public List<ContaBancaria> obterContas(String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = repository.buscarContasDoClienteSql(cli.getId());
		return contasDoCliente;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancarioDTO dto) {
		this.sacar(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem(), dto.getValor(),true, dto);
		this.depositar(dto.getAgenciaDestino(), dto.getNumeroContaDestino(), dto.getValor(),true, dto);
	}
	
	public void depositar (String agencia, String numeroConta, double valor, boolean transferencia, GenericDTO dtoTransferencia) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		if(transferencia) {
			extratoService.formatarExtrato(dtoTransferencia, conta.getSaldo(), TipoTransacaoEnum.TRANSFERENCIA,valor,"+");
		}else {
			DepositoDTO depositoDTO = new DepositoDTO();
			depositoDTO.setAgencia(conta.getAgencia());
			depositoDTO.setNumeroConta(conta.getNumero());
			depositoDTO.setValor(valor);
			extratoService.formatarExtrato(depositoDTO, conta.getSaldo(), TipoTransacaoEnum.DEPOSITO,valor,"+");
		}
		super.salvar(conta);
	}
	
	public void sacar (String agencia, String numeroConta, double valor, boolean transferencia, GenericDTO dtoTransferencia) {
		ContaBancaria conta = this.consultaConta(agencia, numeroConta);
		if (conta.getSaldo() < valor) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INSUFICIENTE);
		}
		conta.setSaldo(conta.getSaldo() - valor);
		if(transferencia) {
			extratoService.formatarExtrato(dtoTransferencia, conta.getSaldo(), TipoTransacaoEnum.TRANSFERENCIA,valor,"-");
		}else {
			SaqueDTO saqueDTO = new SaqueDTO();
			saqueDTO.setAgencia(conta.getAgencia());
			saqueDTO.setNumeroConta(conta.getNumero());
			saqueDTO.setValor(valor);
			extratoService.formatarExtrato(saqueDTO, conta.getSaldo(), TipoTransacaoEnum.SAQUE,valor,"-");
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
