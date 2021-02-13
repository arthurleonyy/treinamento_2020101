package com.indracompany.treinamento.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.treinamento.model.dto.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "extratos")
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long> {

	private static final long serialVersionUID = 3223066331897664518L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_conta_id", nullable = false)
	private ContaBancaria contaBancaria;

	@Column(name = "dataTransacao", nullable = false)
	private LocalDate data;
	
	@Column(name = "tipoTransacao", nullable = false)
	private String tipoTransacao;

	@Column(name = "saldoFinalTrasacao", nullable = false)
	private double saldoFinalTrasacao;

	@Column(name = "valorTrasacao", nullable = false)
	private String valorTrasacao;
}
