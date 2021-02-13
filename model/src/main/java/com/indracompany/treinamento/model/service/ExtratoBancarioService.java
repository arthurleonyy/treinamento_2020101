package com.indracompany.treinamento.model.service;

import com.google.common.collect.Lists;

import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;


import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;  
import java.util.Calendar;
import java.util.List;

@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository> {
	
	@Autowired
	private ExtratoBancarioRepository transacao; 
	 
	
	
	public List<ExtratoBancario>  buscarExtratoPorConta(String agencia, String conta) {
		
		return repository.findByAgenciaAndConta(agencia,conta);
	}
	
	
	/*
	 * public List<ExtratoBancario> obterExtratoPorData(String agencia,String
	 * conta,Date dataInicio,Date dataFim) {
	 * 
	 * List<ExtratoBancario> Extrato = repository.buscarEntreDatasSQLteste(agencia,
	 * conta, dataInicio, dataFim);
	 * 
	 * return Extrato;
	 * 
	 * }
	 * 
	 * public List<ExtratoBancario> buscarExtratoEntreDatas(Date dataInicio, Date
	 * dataFim) {
	 * 
	 * return repository.findAllByPublicationTimeBetween(dataInicio, dataFim); }
	 */
	


	public void geraExtrato(ContaBancaria contaBancaria , double valor, String transacao) {
		Date dataTransacao =  Calendar.getInstance().getTime(); 
		//DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 	
		//formatoData.format(dataTransacao);  
		
		
		ExtratoBancario trans = new ExtratoBancario();
		
		trans.setCliente(contaBancaria.getCliente());
		trans.setConta(contaBancaria.getNumero());
		trans.setAgencia(contaBancaria.getAgencia());			
		trans.setTransacao(transacao);
		trans.setData(dataTransacao);
		trans.setValor(valor);
		this.salvar(trans);
	}

	public List<ExtratoBancario> extratoContaCliente() {
		return Lists.newArrayList(transacao.findAll());
	}
	
	
	
}
