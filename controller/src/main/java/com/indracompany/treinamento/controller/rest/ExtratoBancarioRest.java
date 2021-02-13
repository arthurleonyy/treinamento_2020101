package com.indracompany.treinamento.controller.rest;


import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.springframework.beans.factory.annotation.Autowired;
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


import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;



@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService> {
	
	@RequestMapping(value = "/consultar_extrato/{agencia}/{conta}/{dataInicio}/{dataFim}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody  ResponseEntity<List<ExtratoBancario>> exibirExtratoDaContaEntreDatas(final @PathVariable ExtratoBancarioDTO dto){
	List<ExtratoBancario> extratoDoClientePorContaEntreDatas = getService().obterExtratoPorData(dto.getAgencia(), dto.getConta(), dto.getDataInicio(), dto.getDataFim());
		return new ResponseEntity<>(extratoDoClientePorContaEntreDatas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultar_extrato/{agencia}/{conta}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody  ResponseEntity<List<ExtratoBancario>> exibirExtratoPorConta(final @PathVariable String agencia, final @PathVariable String conta){
		List<ExtratoBancario>  extratoDoClientePorConta = getService().buscarExtratoPorConta(agencia, conta);
		return new ResponseEntity<>(extratoDoClientePorConta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultar_extrato/{dataInicio}/{dataFim}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody  ResponseEntity<List<ExtratoBancario>> exibirExtratoEntreDatas(final @PathVariable Date dataInicio, final @PathVariable Date dataFim){
		List<ExtratoBancario>  extratoDoClientePorConta = getService().buscarExtratoEntreDatas(dataInicio, dataFim);
		return new ResponseEntity<>(extratoDoClientePorConta, HttpStatus.OK);
	}
	
	
}
