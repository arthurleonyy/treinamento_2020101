package com.indracompany.treinamento.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "extratos")
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "agencia")
	private String agencia;
	
	@Column(name = "conta")
	private String conta;
	
	@Column(name = "acao")
	private String acao;
	
	@Column(name = "data")
	private String data;
	
	

}
