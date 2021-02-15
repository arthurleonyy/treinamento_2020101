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

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "extrato")
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long> {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_conta_bancaria_id", nullable = false)
	private ContaBancaria contaId;
	
	@Column(name = "data_transacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date data_transacao;
	
	@Column(name = "tipo_transacao", nullable = false, length = 1)
	private String tipo_transacao; // 1-Transferência 2-Saque 3-Deposito
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "fk_conta_origem_id", nullable = true)
	private ContaBancaria contaOrigemId; // Em caso de saque ou deposito, este valor será null

	

}
