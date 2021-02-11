package com.indracompany.treinamento.controller.rest;

import java.util.List;

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

import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ContaBancariaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/conta")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService> {
	
	@RequestMapping(value = "/consultar-saldo/{agencia}/{numConta}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Double> consultarSaldo(final @PathVariable String agencia, 
			final @PathVariable String numConta) {
		double saldo = getService().consultarSaldo(agencia, numConta);
		return  new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deposito", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> depositar(final @RequestBody DepositoDTO dto) {
		getService().depositar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor());
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> sacar(final @RequestBody SaqueDTO dto) {
		getService().sacar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor());
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Realiza uma transferencia bancaria", nickname = "transferencia")
	@RequestMapping(value = "/transferencia", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> transferir(final @ApiParam("JSON com dados necessarios para realizar uma transferencia")
			@RequestBody TransferenciaBancariaDTO dto) {
		getService().transferir(dto);
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultar-contas-cliente/{cpf}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ContaBancaria>> consultarContaCliente(final @PathVariable String cpf ) {
		List<ContaBancaria> contasDoCliente = getService().obterContas(cpf);
		return  new ResponseEntity<>(contasDoCliente, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/consultar-extrato-bancario/{agencia}/{numConta}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> consultarExtratoBancario(final @PathVariable String agencia, 
			final @PathVariable String numConta) {
		List<ExtratoBancario> extratoBancario = getService(). ;
		return  new ResponseEntity<>(extratoBancario, HttpStatus.OK);
	}*/

}
