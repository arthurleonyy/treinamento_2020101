package com.indracompany.treinamento.model.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.TransacaoExtrato;
import com.indracompany.treinamento.model.entity.enums.TransacaoEnum;
import com.indracompany.treinamento.model.repository.TransacaoExtratoRepository;

@Service
public class TransacaoExtratoService extends GenericCrudService<TransacaoExtrato, Long, TransacaoExtratoRepository> {

	@Autowired
	private TransacaoExtratoRepository ter;

	public void geraExtrato(ContaBancaria contaBancaria, double valor, TransacaoEnum transacao) {
		TransacaoExtrato te = new TransacaoExtrato();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		te.setConta(contaBancaria.getCliente());
		te.setNumero(contaBancaria.getNumero());
		te.setAgencia(contaBancaria.getAgencia());
		te.setTipo(transacao);
		te.setDataTransacao(formatter.format(ZonedDateTime.now()));
		te.setValor(valor);
		this.salvar(te);
	}

	public List<TransacaoExtrato> extratoContaCliente() {
		return Lists.newArrayList(ter.findAll());
	}
}
