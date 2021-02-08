package com.indracompany.treinamento.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.treinamento.model.entity.enums.TransacaoExtratoEnum;

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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransacaoExtratoEnum Transacao;

	private double valor;

	private Instant date;

	@ManyToOne
	@JoinColumn(name = "fk_conta_id", nullable = false)
	private ContaBancaria contaId;
}
