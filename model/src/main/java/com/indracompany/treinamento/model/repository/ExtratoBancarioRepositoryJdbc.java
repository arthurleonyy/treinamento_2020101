package com.indracompany.treinamento.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public class ExtratoBancarioRepositoryJdbc {
	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 * 
	 * public List<ExtratoBancario> retirarExtrato(String agencia, String conta,
	 * Date dataInicio, Date dataFim){ //SELECT * FROM extrato WHERE agencia=''and
	 * //conta='' and DATE( data ) between '2017-01-13' and '2017-01-20';
	 * 
	 * StringBuilder sql = new StringBuilder(" select * from extrato where  ");
	 * 
	 * if (agencia != null && !agencia.isBlank()) {
	 * sql.append(" agencia ='%"+agencia+"%'"); }
	 * 
	 * if (conta != null && !conta.isBlank()) {
	 * 
	 * sql.append(" and conta ='%"+conta+"%'"); }
	 * 
	 * sql.append(" and DATE >'%" +dataInicio +"%'");
	 * 
	 * return jdbcTemplate.query(sql.toString(), new ContaRowMapper()); }
	 * 
	 * 
	 * private class ContaRowMapper implements RowMapper<ExtratoBancario>{
	 * 
	 * @Override public ExtratoBancario mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { ExtratoBancario extrato = new ExtratoBancario();
	 * extrato.setId(rs.getLong("id")); extrato.setAgencia(rs.getString("agencia"));
	 * extrato.setConta(rs.getString("conta"));
	 * 
	 * extrato.setData(rs.getDate("data"));
	 * extrato.setTransacao(rs.getString("transacao"));
	 * extrato.setValor(rs.getDouble("transacao")); return extrato; } }
	 */
	 }
