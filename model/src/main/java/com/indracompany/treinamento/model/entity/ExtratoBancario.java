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
	
	
	public ExtratoBancario() {
		
	}
	
	public ExtratoBancario(String relatorio, double valor, ContaBancaria contaOrigem, ContaBancaria contaDestino, Cliente cliente, String data) {
		this.setCliente(cliente);
		this.setContaBancariaDestino(contaDestino);
		this.setContaBancariaOrigem(contaOrigem);
		this.setData(data);
		this.setRelatorio(relatorio);
		this.setValor(valor);
	}
	
	
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
	@JoinColumn(name = "fk_bank_account_origin_id")
	private ContaBancaria contaBancariaOrigem;
	
	@ManyToOne
	@JoinColumn(name = "fk_bank_account_dest_id")
	private ContaBancaria contaBancariaDestino;
	
	@ManyToOne
	@JoinColumn(name = "fk_client_id")
	private Cliente cliente;
	
}
