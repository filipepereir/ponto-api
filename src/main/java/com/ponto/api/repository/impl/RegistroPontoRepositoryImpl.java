package com.ponto.api.repository.impl;


import com.ponto.api.entity.dto.RegistroPontoUsuariosDTO;
import com.ponto.api.enums.StatusRegistroPonto;
import com.ponto.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class RegistroPontoRepositoryImpl {

    @Autowired
    private EntityManager em;

    @SuppressWarnings("unused")
    public Long buscarQuantidadeBatidasTotaisPorDia(Integer codigoUsuario) {
        try {

            String sql = "SELECT COUNT(*) FROM RegistroPonto rp join rp.usuario u where rp.dataBatida = :dataHoje and u.codigo = :codigoUsuario";

            TypedQuery<Long> query = em.createQuery(sql, Long.class);
            query.setParameter("dataHoje", DateUtils.dataAtual());
            query.setParameter("codigoUsuario", codigoUsuario);

            return query.getSingleResult();

        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unused")
    public Long buscarQuantidadeBatidasErroPorDia(Integer codigoUsuario) {
        try {

            String sql = "SELECT COUNT(*) FROM RegistroPonto rp join rp.usuario u where rp.dataBatida = :dataHoje and u.codigo = :codigoUsuario and rp.status = :status";

            TypedQuery<Long> query = em.createQuery(sql, Long.class);
            query.setParameter("dataHoje", DateUtils.dataAtual());
            query.setParameter("codigoUsuario", codigoUsuario);
            query.setParameter("status", StatusRegistroPonto.ERRO);

            return query.getSingleResult();

        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unused")
    public List<RegistroPontoUsuariosDTO> buscarRegistrosUsuariosV2() {
        try {

            String sql = "SELECT new com.ponto.api.entity.dto.RegistroPontoUsuariosDTO ( " +
                    "rp.dataHoraBatida, rp.dataBatida, rp.status, rp.mensagem, u.nome, u.codigo) " +
                    " from RegistroPonto rp join rp.usuario u";

            TypedQuery<RegistroPontoUsuariosDTO> query = em.createQuery(sql, RegistroPontoUsuariosDTO.class);

            return query.getResultList();

        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }


}
