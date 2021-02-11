package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;

@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository >{	
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	public void atualizarExtrato (String relatorio, double valor, ContaBancaria contaOrigem, ContaBancaria contaDestino, Cliente cliente, String data) {	
		ExtratoBancario extrato = new ExtratoBancario(relatorio,valor,contaOrigem,contaDestino,cliente,data);				
		super.salvar(extrato);			
	}
	
	public List<ExtratoBancario> listarExtrato(String agencia, String numeroConta) {
		ContaBancaria conta = contaBancariaService.consultarConta(agencia,numeroConta);
		List<ExtratoBancario> extratoDoCliente = repository.findByContaBancariaOrigem(conta);
		
		//método abaixo serve para adicionar ao extrato da conta bancaria requisitada as transferencias bancárias recebidas
		List<ExtratoBancario> tempList = repository.findByContaBancariaDestino(conta);
		if (!tempList.isEmpty()) {			
			int i = 0;
			for (ExtratoBancario ebTemp: tempList) {
				if (extratoDoCliente.isEmpty()) {
					extratoDoCliente.add(ebTemp);
				}else {
					for(ExtratoBancario ebOriginal: extratoDoCliente) {
						if (ebTemp.getId() > ebOriginal.getId()){
							i++;						
						}else {
							extratoDoCliente.add(i, ebTemp);
							i = 0;
							break;
						}
					}
				}
			}
		}
		
		
		return extratoDoCliente;
	}
	
	
	
}
