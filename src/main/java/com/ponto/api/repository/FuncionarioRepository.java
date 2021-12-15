package com.ponto.api.repository;

import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ponto.api.entity.Usuario;
import com.ponto.api.entity.dto.UsuarioDTO;

public interface FuncionarioRepository extends JpaRepository<Usuario, Integer> {

	public UsuarioDTO buscarUsuarioPorId(Integer codigo) throws NoResultException;

	Usuario findByEmail(String email);
}
