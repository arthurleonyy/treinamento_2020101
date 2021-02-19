package com.indracompany.treinamento.controller.rest;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.Extrato;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService>{
	
	
		
	@RequestMapping(value = "/criar-extrato-bancario-por-periodo/{agencia}/{conta}/{datai}/{dataf}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public  @ResponseBody ResponseEntity<List<ExtratoBancario>> criarExtratoBancarioPorPeriodo(final Extrato dto){
		List<ExtratoBancario> acoes = getService().criarExtratoPorPeriodo(dto.getAgencia(), dto.getConta(), dto.getDatai(), dto.getDataf());
		return new ResponseEntity<>(acoes, HttpStatus.OK);
	}

}
