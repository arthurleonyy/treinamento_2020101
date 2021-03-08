package com.indracompany.treinamento.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.indracompany.treinamento.util.TiposTransacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class ExtratoDTO {
	
	public Long id;
	public String tipoTransacao;
	public String descricao;
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date data;
	public Double valor;
	public ExtratoDTO(Long id, String tipoTransacao, String descricao, Date data, Double valor) {
		super();
		this.id = id;
		this.tipoTransacao = TiposTransacao.findByValue(tipoTransacao).toString().split("_")[0];
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
	}
	
	

}
