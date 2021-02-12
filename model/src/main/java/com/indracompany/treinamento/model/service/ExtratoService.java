package com.indracompany.treinamento.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.dto.GenericDTO;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.enums.TipoTransacaoEnum;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{

	@Autowired
	private ExtratoRepository extratoRepository;

	public List<Extrato> extratoMesAno(String mes,String ano) {
		String mesAnoFormatado = ano+"-"+mes;
		List<Extrato> extratoMesAno = extratoRepository.buscarExtratoMesAno(mesAnoFormatado);
		return extratoMesAno;
	}

	
	public void formatarExtrato(GenericDTO dadosBancario, double saldoFinalTrasacao, TipoTransacaoEnum tipoTransacao, double valorTrasacao, String sinalOperação) {
		Extrato extrato = new Extrato();
		extrato.setTipoTransacao(tipoTransacao.getTipo());
		extrato.setSaldoFinalTrasacao(saldoFinalTrasacao);
		extrato.setValorTrasacao(sinalOperação + " " + String.valueOf(valorTrasacao));
		extrato.setData(LocalDate.now());
		extrato.setDadosBancario(dadosBancario);
		this.salvar(extrato);
	}
	
}
