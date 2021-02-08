package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indracompany.treinamento.util.TiposTransacao;

import javax.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "registro_transacoes")
@EqualsAndHashCode(callSuper = true)
public class RegistroTransacoes extends GenericEntity<Long> {

	private static final long serialVersionUID = -740766305571711645L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "gk_id_conta", nullable = false)
	private ContaBancaria conta;
	
	@Column(nullable = true,length = 50)
	private String descricao;
	
	
	@Column(name = "tipo_transacao", nullable = false)
	private String tipoTransacao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(nullable = false)
	private Double valor;
	
	public RegistroTransacoes() {}
	
	public RegistroTransacoes(Long id, String tipo, String descricao, Date data, Double valor) {
		this.id = id;
		this.tipoTransacao = tipo;
		this.descricao = descricao;
		
		this.data = data;
		this.valor = valor;
	}
	
}