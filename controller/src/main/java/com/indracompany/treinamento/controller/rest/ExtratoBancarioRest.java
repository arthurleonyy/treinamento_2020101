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
import com.indracompany.treinamento.model.service.ExtratoBancarioService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extratos")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService> {

	
	@RequestMapping(value = "/consultar-extrato-bancario/{agencia}/{numConta}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> consultarExtratoBancario(final @PathVariable String agencia, 
			final @PathVariable String numConta) {
		List<ExtratoBancario> extratoBancario = getService().listarExtrato(agencia, numConta);
		return  new ResponseEntity<>(extratoBancario, HttpStatus.OK);
	}
}
