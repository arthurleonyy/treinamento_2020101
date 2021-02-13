package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "extrato")
@EqualsAndHashCode(callSuper = true)
public class ExtratoBancario extends GenericEntity<Long> {
	
	private static final long serialVersionUID = -6751299318814431260L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id", nullable = false)
	private Cliente cliente;
	
	@Column(name = "agencia", nullable = false)
	private String agencia;
	
	@Column(name = "conta", nullable = false)
	private String conta;
	
	@Column(name = "operacao", nullable = false)
	private String operacao;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data")
	private Date data;
	
	
	@Column(name = "contaOrigem", nullable = true)
	private String contaOrigem;
	
	@Column(name = "contaDestino", nullable = true)
	private String contaDestino;
	
	@Column(name = "saldo", nullable = false)
	private double saldo;
	
	
}
