package com.indracompany.treinamento.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ExtratoRest extends GenericCrudRest<Extrato, Long,ExtratoService>{
	
	@Autowired
	private ExtratoService extratoService; 
	
	@RequestMapping(value = "/consultar-extrato/{contaId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Extrato> consultarSaldo(final @PathVariable Long contaId){
		Extrato extrato = extratoService.consultarExtrato(contaId);
		return new ResponseEntity<>(extrato, HttpStatus.OK);
	}
}
