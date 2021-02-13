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

import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.service.ExtratoService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoBancarioRest extends GenericCrudRest<Extrato, Long, ExtratoService>{
	
	
	@RequestMapping(value = "/consultar-extrato/{agencia}/{numConta}/{data}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<Extrato>> extrato(final @PathVariable String agencia, 
			final @PathVariable String numConta, final @PathVariable String data){
		List<Extrato> extrato = getService().buscarExtrato(agencia, numConta, data);
		return new ResponseEntity<>(extrato, HttpStatus.OK);
	}

}
