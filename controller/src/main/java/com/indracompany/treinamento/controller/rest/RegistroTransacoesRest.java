package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.BuscaExtratoDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.RegistroTransacoes;
import com.indracompany.treinamento.model.service.RegistroTransacoesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("rest/registro-transacoes")
public class RegistroTransacoesRest extends GenericCrudRest<RegistroTransacoes, Long, RegistroTransacoesService>{

	@RequestMapping(value = "/consultar-extrato", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoDTO>> consultarExtrato(final BuscaExtratoDTO dto) {
		
		List<ExtratoDTO> listaRegistros = getService().getEstrato(dto);
		return new ResponseEntity<>(listaRegistros, HttpStatus.OK);
		
	}
}
