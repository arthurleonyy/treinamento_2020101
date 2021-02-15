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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "extrato")
@EqualsAndHashCode(callSuper = true)
public class ExtratoBancario extends GenericEntity<Long>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2330018194241030142L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id", nullable = false)
	private Cliente cliente; 
	
	@Column(name = "conta", nullable = false)
	private String conta;

	@Column(name = "agencia", nullable = false)
	private String agencia;
	
		
	@Column(name = "transacao", nullable = false)
	private String transacao;
	
	@Column(name = "data", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data; 
	
	@Column(name = "valor", nullable = false)
	private double valor; 
	

}
