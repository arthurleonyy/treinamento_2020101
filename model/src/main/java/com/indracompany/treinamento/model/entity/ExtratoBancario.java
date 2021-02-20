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
@Table(name = "extratos")
@EqualsAndHashCode(callSuper = true)
public class ExtratoBancario extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496546884476096750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_cliente_id", nullable = false)
	private Cliente cliente;

	@Column(name = "agencia", nullable = false, length = 4)
	private String agencia;

	@Column(name = "numero", nullable = false, length = 6)
	private String numero;

	@Column(name = "saldo", nullable = false)
	private double saldo;
	
	@Column(name = "operacao", nullable = false)
	private String operacao;
	
	@Column(name = "valor_em_transito", nullable = false)
	private String valor;
	
//	@Column(name = "data", nullable = false)
//	private String data;
}
