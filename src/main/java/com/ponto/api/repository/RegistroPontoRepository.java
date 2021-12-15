package com.ponto.api.repository;

import com.ponto.api.entity.RegistroPonto;
import com.ponto.api.entity.dto.RegistroPontoUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Integer> {

    Long buscarQuantidadeBatidasTotaisPorDia(Integer codigoUsuario);

    Long buscarQuantidadeBatidasErroPorDia(Integer codigoUsuario);

    List<RegistroPontoUsuarioDTO> buscarRegistrosUsuariosV2();
}
