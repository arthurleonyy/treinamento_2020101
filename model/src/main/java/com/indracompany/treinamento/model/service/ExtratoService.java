package com.indracompany.treinamento.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.enums.TipoTransacaoEnum;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{

	@Autowired
	private ExtratoRepository extratoRepository;

	public List<Object> extratoMesAno(String mes,String ano,String numConta,String numAgencia) {
		String mesAnoFormatado = ano+"-"+mes;
		List<Object> extratoMesAno = extratoRepository.buscarExtratoMesAno(mesAnoFormatado,numConta,numAgencia);
		return extratoMesAno;
	}

	
	public void formatarExtrato(ContaBancaria dadosBancario, double saldoFinalTrasacao, TipoTransacaoEnum tipoTransacao, double valorTrasacao, String sinalOperação) {
		Extrato extrato = new Extrato();
		extrato.setTipoTransacao(tipoTransacao.getTipo());
		extrato.setSaldoFinalTrasacao(saldoFinalTrasacao);
		extrato.setValorTrasacao(sinalOperação + " " + String.valueOf(valorTrasacao));
		extrato.setData(LocalDate.now());
		extrato.setContaBancaria(dadosBancario);
		this.salvar(extrato);
	}
	
}
