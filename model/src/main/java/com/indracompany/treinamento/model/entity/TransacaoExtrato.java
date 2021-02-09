package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.treinamento.model.entity.enums.TransacaoEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "transacao_extrato")
@EqualsAndHashCode(callSuper = true)
public class TransacaoExtrato extends GenericEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transacao_extrato", nullable = false)
	private TransacaoEnum tipo;

	@Column(name = "numero_conta", nullable = false)
	private String numero;

	@Column(name = "numero_agencia", nullable = false)
	private String agencia;

	@Column(name = "valor_transacao", nullable = false)
	private double valor;

	@Column(name = "data_transacao", nullable = false)
	private String transacao;

	@ManyToOne
	@JoinColumn(name = "fk_conta_cliente_id", nullable = false)
	private Cliente conta;
}
