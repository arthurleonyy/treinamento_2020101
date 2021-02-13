package com.indracompany.treinamento.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{
	
	@Autowired
	private ExtratoRepository extratoRepository;
	
	public List<Extrato> buscarExtrato(String agencia, String numero, String data){
		List<Extrato> buscarExtrato = extratoRepository.buscarExtrato(agencia, numero, data);
		return buscarExtrato;
	}
	
	public void registrar(ContaBancaria contaBancaria, double valor, String tipoTransacao) {
		Extrato extrato = new Extrato();
		extrato.setAgencia(contaBancaria.getAgencia());
		extrato.setNumero(contaBancaria.getNumero());
		extrato.setData_transacao(LocalDate.now().toString());
		extrato.setId_cliente(contaBancaria.getCliente().getId());
		extrato.setDescricao(tipoTransacao);
		extrato.setValor(valor);
		extrato.setSaldo_conta(contaBancaria.getSaldo());
		
		this.salvar(extrato);
	}

}
