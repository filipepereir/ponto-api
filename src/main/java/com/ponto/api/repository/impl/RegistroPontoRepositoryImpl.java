package com.ponto.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ponto.api.entity.dto.RegistroPontoUsuarioDTO;
import com.ponto.api.enums.StatusRegistroPonto;
import com.ponto.api.utils.DateUtils;

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
	public List<RegistroPontoUsuarioDTO> buscarRegistrosUsuariosV2() {
		try {

			String sql = "SELECT new com.ponto.api.entity.dto.RegistroPontoUsuarioDTO ( "
					+ "rp.dataHoraBatida, rp.dataBatida, rp.status, rp.mensagem, u.nome, u.codigo) "
					+ " from RegistroPonto rp join rp.usuario u";

			TypedQuery<RegistroPontoUsuarioDTO> query = em.createQuery(sql, RegistroPontoUsuarioDTO.class);

			return query.getResultList();

		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
	}

}
