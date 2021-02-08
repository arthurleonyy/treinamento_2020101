package com.indracompany.treinamento.model.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.TransacaoExtrato;
import com.indracompany.treinamento.model.entity.enums.TransacaoExtratoEnum;
import com.indracompany.treinamento.model.repository.TransacaoExtratoRepository;

@Service
public class TransacaoExtratoService extends GenericCrudService<TransacaoExtrato, Long, TransacaoExtratoRepository> {

	@Autowired
	private TransacaoExtratoRepository ter;

	public List<TransacaoExtrato> extratoContaCliente() {
		return Lists.newArrayList(ter.findAll());
	}

	public void geraExtrato(ContaBancaria contaBancaria, double valor, TransacaoExtratoEnum transacao) {
		TransacaoExtrato te = new TransacaoExtrato();
		te.setTransacao(transacao);
		te.setValor(valor);
		te.setDate(Instant.now());
		te.setContaId(contaBancaria);
		this.salvar(te);
	}
}
