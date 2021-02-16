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

import com.indracompany.treinamento.model.dto.ExtratoDataDTO;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.service.ExtratoService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoRest extends GenericCrudRest<Extrato, Long, ExtratoService>{
	
	
	@RequestMapping(value = "/gerar-gxtrato/{agencia}/{conta}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<Extrato>> gerarExtrato(final @PathVariable String agencia, final @PathVariable String conta){
		List<Extrato> acoes = getService().gerarExtrato(agencia, conta);
		return new ResponseEntity<>(acoes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gerar-gxtrato-data/{agencia}/{conta}/{data1}/{data2}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public  @ResponseBody ResponseEntity<List<Extrato>> gerarExtratoData(final ExtratoDataDTO dto){
		List<Extrato> acoes = getService().gerarExtratoData(dto.getAgencia(), dto.getConta(), dto.getData1(), dto.getData2());
		return new ResponseEntity<>(acoes, HttpStatus.OK);
	}

}
