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

@Data
@Entity
@Table(name = "extrato_bancario")
public class ExtratoBancario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "tipo", nullable = false)
	private String tipoMovimento;
	
	@Column(name = "valor")
	private String valor;
	
	@ManyToOne
	@JoinColumn(name = "fk_bank_account_origin_id", nullable = false)
	private ContaBancaria contaBancariaOrigem;
	
	@ManyToOne
	@JoinColumn(name = "fk_bank_account_destination_id")
	private ContaBancaria contaBancariaDestino;
	
	@ManyToOne
	@JoinColumn(name = "fk_client_id")
	private Cliente cliente;
	

}
