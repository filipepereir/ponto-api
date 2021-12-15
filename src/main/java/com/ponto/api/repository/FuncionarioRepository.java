package com.ponto.api.repository;


import com.ponto.api.entity.Usuario;
import com.ponto.api.entity.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NoResultException;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Usuario, Integer> {

    //@Query("SELECT new com.folha.pontoApi.entity.dto.UsuarioDTO(u.codigo, u.nome) from Usuario u where u.codigo = :codigo")
    public UsuarioDTO buscarUsuarioPorId(Integer codigo) throws NoResultException;

    Usuario findByEmail(String email);
}
