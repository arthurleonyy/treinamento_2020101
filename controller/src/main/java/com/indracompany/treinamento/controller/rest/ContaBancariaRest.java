package com.indracompany.treinamento.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.TransacaoDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;

import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("rest/conta")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService> {
	
	@RequestMapping(value = "/consultar-saldo/{agencia}/{numeroConta}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Double> consultarSaldo(final @PathVariable String agencia, final @PathVariable String numeroConta) {
		Double valor = getService().consultarSaldo(agencia, numeroConta);
		return new ResponseEntity<>(valor,HttpStatus.OK);
	}

	@RequestMapping(value = "/deposito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> depositar(final @RequestBody TransacaoDTO dto) {
		getService().depositar(dto.getAgencia(), dto.getConta(), dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saque(final @RequestBody TransacaoDTO dto) {
		getService().sacar(dto.getAgencia(), dto.getConta(), dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/transferencia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> transferencia(final @RequestBody TransferenciaBancariaDTO dto) {
		getService().transferir(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
