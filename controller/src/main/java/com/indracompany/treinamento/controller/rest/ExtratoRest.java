package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoService>{

	@ApiOperation(value = "Realiza a emissão de extrato bancário", nickname = "extrato")
	@RequestMapping(value = "/emitir-extrato/{agencia}/{conta}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> emitirExtrato(final @ApiParam("Número da agência") @PathVariable String agencia, final @ApiParam("Número da conta") @PathVariable String conta){
		List<ExtratoBancario> extrato = getService().emitirExtrato(agencia, conta);
		return new ResponseEntity<>(extrato, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Emissão de extrato bancário por data", nickname = "extratoPorData")
	@RequestMapping(value = "/emitir-extrato-com-data/{agencia}/{conta}/{dataInicio}/{dataFim}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> emitirExtratoComData(final @PathVariable String agencia, final @PathVariable String conta, final @PathVariable String dataInicio, final @PathVariable String dataFim){
		List<ExtratoBancario> extrato = getService().emitirExtratoComData(agencia, conta, dataInicio, dataFim);
		
		return new ResponseEntity<>(extrato, HttpStatus.OK);
	}
	
}
