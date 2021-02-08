package com.indracompany.treinamento.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.RegistroTransacoes;
import com.indracompany.treinamento.model.repository.RegistroTransacoesRepository;
import com.indracompany.treinamento.util.TiposTransacao;

@Service
public class RegistroTransacoesService extends GenericCrudService<RegistroTransacoes, Long, RegistroTransacoesRepository> {

	public void registraTransacao(ContaBancaria conta,  Double valor, TiposTransacao tipo) {
		this.registraTransacao(conta, valor, tipo, null);
	}
	
	public void registraTransacao(ContaBancaria conta, Double valor, TiposTransacao tipo, String descricao) {
		RegistroTransacoes r = new RegistroTransacoes();
		r.setData(new Date(System.currentTimeMillis()));
		r.setConta(conta);
		r.setValor(valor);
		r.setTipoTransacao(tipo.getTipo());
		r.setDescricao(descricao);
		
		super.salvar(r);
	}
	
	public List<RegistroTransacoes> getEstrato(ExtratoDTO dto) {
		if(dto.getDataInicio().after(dto.getDataFim())) throw new AplicacaoException(ExceptionValidacoes.ERRO_PERIODO_INVALIDO);
		
		List<RegistroTransacoes> listaRegistros = getRepository().getExtrato(dto.getAgencia(), dto.getConta(), dto.getDataInicio(), dto.getDataFim());
		
		if(listaRegistros.isEmpty()) throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUMA_OPERACAO_REGISTRADA);
		return listaRegistros;
				
	}
}
