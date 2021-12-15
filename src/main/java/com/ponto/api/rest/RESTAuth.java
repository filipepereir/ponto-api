package com.ponto.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.api.entity.dto.security.LoginDTO;
import com.ponto.api.entity.dto.security.UsuarioLoginDTO;
import com.ponto.api.service.security.AuthService;
import com.ponto.api.service.security.FuncionarioDetailsService;
import com.ponto.api.service.security.TokenService;

@RestController
@CrossOrigin
public class RESTAuth {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService service;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private FuncionarioDetailsService funcionarioDetailsService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
		final UserDetails userDetails = funcionarioDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = tokenService.gerarToken(userDetails);
		return ResponseEntity.ok(new UsuarioLoginDTO(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
