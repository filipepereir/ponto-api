package com.ponto.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ponto.api.entity.dto.UsuarioDTO;
import com.ponto.api.repository.FuncionarioRepository;

public class RESTUtils {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public UsuarioDTO getUsuarioLogado() {
		return funcionarioRepository.buscarUsuarioDtoPorEmail(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}

}
