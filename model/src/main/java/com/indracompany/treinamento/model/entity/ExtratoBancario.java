package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "extrato_bancario")
@EqualsAndHashCode(callSuper = true)
public class ExtratoBancario extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "relatorio", nullable = false)
	private String relatorio;
	
	@Column(name = "valor")
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "fk_bank_account_origin_id", nullable = false)
	private ContaBancaria contaBancariaOrigem;
	
}
