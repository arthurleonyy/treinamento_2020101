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

import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/extrato")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService> {

	@RequestMapping(value = "/consultar_extrato/{dto}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> exibirExtratoDaContaEntreDatas(
			final ExtratoBancarioDTO dto) {
		List<ExtratoBancario> extratoDoClientePorContaEntreDatas = getService().buscarExtratoEntreDatas(dto);
		return new ResponseEntity<>(extratoDoClientePorContaEntreDatas, HttpStatus.OK);
	}

	@RequestMapping(value = "/consultar_extrato/{agencia}/{conta}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> exibirExtratoPorConta(final @PathVariable String agencia,
			final @PathVariable String conta) {
		List<ExtratoBancario> extratoDoClientePorConta = getService().buscarExtratoPorConta(agencia, conta);
		return new ResponseEntity<>(extratoDoClientePorConta, HttpStatus.OK);
	}

}
