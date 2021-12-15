package com.ponto.api.service.security;

import com.ponto.api.entity.Usuario;
import com.ponto.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FuncionarioDetailsService implements UserDetailsService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario user = funcionarioRepository.findByEmail(email);

		if (user.getEmail().equals(email)) {
			return new User(email, user.getSenha(), new ArrayList<>());
		} else {
			return null;
		}

	}
}
