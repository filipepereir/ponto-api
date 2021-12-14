package com.ponto.api.repository.impl;

import com.ponto.api.entity.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class FuncionarioRepositoryImpl {


    @Autowired
    private EntityManager em;

    @SuppressWarnings("unused")
    public UsuarioDTO buscarUsuarioPorId(Integer codigo) throws NoResultException {
        try {

            String sql = "SELECT new com.ponto.api.entity.dto.UsuarioDTO(u.codigo, u.nome) from Usuario u where u.codigo = :codigo";

            TypedQuery<UsuarioDTO> query = em.createQuery(sql, UsuarioDTO.class);
            query.setParameter("codigo", codigo);

            return query.getSingleResult();

        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
}
