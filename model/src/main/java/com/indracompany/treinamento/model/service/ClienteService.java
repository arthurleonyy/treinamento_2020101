package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarClientePorCpf(String cpf) {
		if (!cpfEhValido(cpf)){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO);
		}
		return clienteRepository.findByCpf(cpf);
	}
	
	public List<Cliente> buscarClienteComNome(String nome) {
		if (nome == null || nome.equals("")){
			throw new AplicacaoException("O nome não foi informado.");
		}
		return clienteRepository.findByNomeIsContaining(nome);
	}
	
	public Cliente buscarClientePorNome(String nome) {
		if (nome == null || nome.equals("")){
			throw new AplicacaoException("O nome não foi informado.");
		}
		return clienteRepository.findByNome(nome);
	}
	
	private boolean cpfEhValido(String cpf) {
		return CpfUtil.validaCPF(cpf);
	}
	

}
