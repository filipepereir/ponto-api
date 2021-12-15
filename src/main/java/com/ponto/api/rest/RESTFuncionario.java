package com.ponto.api.rest;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.api.entity.dto.UsuarioDTO;

@RestController
@RequestMapping("/funcionario")
public class RESTFuncionario extends RESTUtils {

	@GetMapping
	public ResponseEntity<UsuarioDTO> buscarUsuarioLogado() {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(getUsuarioLogado());
		} catch (NoResultException unee) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
